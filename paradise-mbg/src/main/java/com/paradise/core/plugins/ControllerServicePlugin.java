package com.paradise.core.plugins;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.generator.internal.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * 在 Mybatis Generator 的基础上生成 Controller 与 Service 类,适用于Spring与Mybatis整合的项目.
 * 为 Mybatis Generator 的 Mapper.java 生成对应的 Controller 与 Service 类.
 *
 * @author linweiyu
 */
public class ControllerServicePlugin extends PluginAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String IS_GENERATE_CONTROLLER_SERVICE = "generate.controller.service";

    @Override
    public boolean validate(List<String> warnings) {
        logger.info("--- ControllerServicePlugin validate invoke");
        return true;
    }

    @Override
    public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
        logger.info("--- ControllerServicePlugin contextGenerateAdditionalJavaFiles invoke");
        List<GeneratedJavaFile> generatedJavaFiles = super.contextGenerateAdditionalJavaFiles(introspectedTable);
        Properties properties = getProperties();
        // 是否生成Service,Controller类
        String isGenerateControllerService = properties.getProperty(IS_GENERATE_CONTROLLER_SERVICE);
        logger.info("--- ControllerServicePlugin is_generate_controller_service=[{}]", isGenerateControllerService);
        if (StringUtility.stringHasValue(isGenerateControllerService) && Boolean.parseBoolean(isGenerateControllerService)) {
            if (generatedJavaFiles == null) {
                generatedJavaFiles = new ArrayList<>();
            }
            // 实体类的包名
            String modelTargetPackage = getContext().getJavaModelGeneratorConfiguration().getTargetPackage();
            // 实体类的类名
            String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
            // 实体类的全限定类名
            String modalFullName = modelTargetPackage + "." + domainObjectName;
            logger.info("--- ControllerServicePlugin modalFullName=[{}]", modalFullName);
            // src/main/java
            String targetProject = properties.getProperty("targetProject");
            logger.info("--- ControllerServicePlugin targetProject=[{}]", targetProject);
            // Service与Controller所在的包的包名
            String servicePackage = properties.getProperty("service.package");
//            String serviceImplPackage = servicePackage + ".impl";
            String webPackage = properties.getProperty("web.package");
            logger.info("--- ControllerServicePlugin servicePackage=[{}]", servicePackage);
            logger.info("--- ControllerServicePlugin webPackage=[{}]", webPackage);
            // 生成Service类所在的包(对应文件系统的文件夹),同时生成impl包
            DefaultShellCallback shellCallback = new DefaultShellCallback(true);
            // 生成Service相关类
            generateServiceFile(introspectedTable, generatedJavaFiles, domainObjectName, modalFullName, targetProject, servicePackage, servicePackage);
            // 生成Controller相关类
            generateControllerJavaFile(introspectedTable, generatedJavaFiles, domainObjectName, modalFullName, targetProject, servicePackage, webPackage, shellCallback);
        }

        return generatedJavaFiles;
    }

    private void generateControllerJavaFile(IntrospectedTable introspectedTable, List<GeneratedJavaFile> generatedJavaFiles, String domainObjectName,
                                            String modalFullName, String targetProject, String servicePackage, String webPackage, DefaultShellCallback shellCallback) {
        try {
            File controllerDirectory = shellCallback.getDirectory(targetProject, webPackage);
            String absolutePath = controllerDirectory.getAbsolutePath();
            if (StringUtility.stringHasValue(absolutePath)) {
                logger.info("--- ControllerServicePlugin 创建目录(包)成功=[{}]", absolutePath);
            }
        } catch (ShellException e) {
            logger.error(e.getMessage(), e);
        }
        // ServiceImpl类
        // typeName为类的全限定类名
        String typeName = webPackage + "." + domainObjectName + "Controller";
        // TopLevelClass类代表要生成的类,mybatis根据TopLevelClass类代表要生成一个类的field,method等
        TopLevelClass controllerClass = new TopLevelClass(typeName);
        controllerClass.addJavaDocLine("/**\n" +
                " * " + introspectedTable.getRemarks() + "控制器 \n" +
                " *\n" +
                " * @author Paradise\n" +
                " */");
        controllerClass.setVisibility(JavaVisibility.PUBLIC);

        // 为类添加注解
        controllerClass.addAnnotation("@RestController");
        controllerClass.addAnnotation("@AllArgsConstructor");
        controllerClass.addAnnotation("@Api(tags=\"" + modalFullName + "\")");
        if (domainObjectName.endsWith("s")) {
            controllerClass.addAnnotation("@RequestMapping(\"/" + toLowerCaseFirstOne(domainObjectName) + "\")");
        } else {
            controllerClass.addAnnotation("@RequestMapping(\"/" + toLowerCaseFirstOne(domainObjectName) + "s" + "\")");
        }

        // 为类添加Field
        setControllerField(controllerClass, introspectedTable, servicePackage);

        // 为类添加方法
        setControllerMethod(controllerClass, introspectedTable);

        // 为Service类添加import类
        // 添加实现的接口的包
        controllerClass.addImportedType(new FullyQualifiedJavaType("com.paradise.core.common.api.CommonPage"));
        controllerClass.addImportedType(new FullyQualifiedJavaType("com.paradise.core.common.api.Result"));
        controllerClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.Api"));
        controllerClass.addImportedType(new FullyQualifiedJavaType("io.swagger.annotations.ApiOperation"));
        controllerClass.addImportedType(new FullyQualifiedJavaType("lombok.AllArgsConstructor"));
        controllerClass.addImportedType(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.*"));
        controllerClass.addImportedType(new FullyQualifiedJavaType("org.springframework.validation.annotation.Validated"));
        controllerClass.addImportedType(new FullyQualifiedJavaType(servicePackage + "." + domainObjectName + "Service"));
        controllerClass.addImportedType(new FullyQualifiedJavaType(modalFullName));
        controllerClass.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        GeneratedJavaFile controller = new GeneratedJavaFile(controllerClass, targetProject, "UTF-8", getContext().getJavaFormatter());
        generatedJavaFiles.add(controller);
    }

    private void setControllerMethod(TopLevelClass controllerCompilationUnit, IntrospectedTable introspectedTable) {
        addSelectByPageMethod4Controller(controllerCompilationUnit, introspectedTable);
        addInsertMethod4Controller(controllerCompilationUnit, introspectedTable);
        addUpdateByPrimaryKeyMethod4Controller(controllerCompilationUnit, introspectedTable);
        addSelectByPrimaryKeyMethod4Controller(controllerCompilationUnit, introspectedTable);
        addDeleteByPrimaryKeyMethod4Controller(controllerCompilationUnit, introspectedTable);

//        addInsertSelectiveMethod4Controller(controllerCompilationUnit, introspectedTable);
//        addUpdateByPrimaryKeySelectiveMethod4Controller(controllerCompilationUnit, introspectedTable);
    }

    private void addSelectByPageMethod4Controller(TopLevelClass controllerCompilationUnit, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Result<CommonPage<" + domainObjectName + ">>"));
        method.setName("selectByPage");

        // 添加方法参数
        // offsetParam
        Parameter offsetParam = new Parameter(new FullyQualifiedJavaType("Integer"), "pageNum");
        Parameter limitParam = new Parameter(new FullyQualifiedJavaType("Integer"), "pageSize");
        method.addParameter(offsetParam);
        method.addParameter(limitParam);

        method.addAnnotation("@ApiOperation(value = \"分页查询\")");
        method.addAnnotation("@GetMapping(value = \"/page\")");

        method.addBodyLine("List<" + domainObjectName + "> result = this." + toLowerCaseFirstOne(domainObjectName)
                + "Service.selectByPage(" + getParameterStr(method) + ");");
        method.addBodyLine("return Result.success(CommonPage.restPage(result));");

        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(
                method, controllerCompilationUnit, introspectedTable)) {
            controllerCompilationUnit.addImportedTypes(importedTypes);
            controllerCompilationUnit.addMethod(method);
        }
    }

    private void addUpdateByPrimaryKeyMethod4Controller(TopLevelClass controllerCompilationUnit, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Result<Integer>"));
        method.setName(introspectedTable.getUpdateByPrimaryKeyStatementId());

        // 添加方法参数
        FullyQualifiedJavaType parameterType;
        parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record"));

        // 添加方法注解
        method.addAnnotation("@ApiOperation(\"修改\")");
        method.addAnnotation("@PostMapping(value = \"/update\")");
        List<Parameter> parameters = method.getParameters();
        String sb = parameters.get(0).getName();
        method.addBodyLine("int count = this." + toLowerCaseFirstOne(domainObjectName) + "Service." + introspectedTable.getUpdateByPrimaryKeyStatementId() + "(" + sb + ");");
        method.addBodyLine("if (count > 0) {\n" +
                "            return Result.success(count);\n" +
                "        }\n" +
                "        return Result.failed();");

        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(
                method, controllerCompilationUnit, introspectedTable)) {
            controllerCompilationUnit.addImportedTypes(importedTypes);
            controllerCompilationUnit.addMethod(method);
        }
    }


    private void addSelectByPrimaryKeyMethod4Controller(TopLevelClass controllerCompilationUnit, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Result<" + domainObjectName + ">"));
        method.setName(introspectedTable.getSelectByPrimaryKeyStatementId());

        // 添加方法注解
        method.addAnnotation("@ApiOperation(\"详情\")");
        method.addAnnotation("@GetMapping(value = \"/detail/{id}\")");
        // 添加方法参数
        StringBuilder pathVar = addMethodParameter(introspectedTable, importedTypes, method);
        String sb = getParameterStr(method);
        method.addBodyLine(domainObjectName + " " + toLowerCaseFirstOne(domainObjectName) + " = this." +
                toLowerCaseFirstOne(domainObjectName) + "Service." + introspectedTable.getSelectByPrimaryKeyStatementId() + "(" + sb + ");");
        method.addBodyLine("return Result.success(" + toLowerCaseFirstOne(domainObjectName) + ");");
        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(
                method, controllerCompilationUnit, introspectedTable)) {
            controllerCompilationUnit.addImportedTypes(importedTypes);
            controllerCompilationUnit.addMethod(method);
        }
    }

    private void addInsertSelectiveMethod4Controller(TopLevelClass controllerCompilationUnit, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Result<Integer>"));
        method.setName(introspectedTable.getInsertSelectiveStatementId());

        // 添加方法参数
        FullyQualifiedJavaType parameterType;
        parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record"));

        // 添加方法注解
        method.addAnnotation("@RequestMapping(value = \"/selective\", method = RequestMethod.POST, produces = {\"application/json;charset=UTF-8\"})");

        method.addBodyLine("int resultCount = this." + toLowerCaseFirstOne(domainObjectName) + "Service." + introspectedTable.getInsertSelectiveStatementId() + "(record);");
        method.addBodyLine("return Result.success(resultCount);;");

        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(
                method, controllerCompilationUnit, introspectedTable)) {
            controllerCompilationUnit.addImportedTypes(importedTypes);
            controllerCompilationUnit.addMethod(method);
        }
    }

    private void addInsertMethod4Controller(TopLevelClass controllerCompilationUnit, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Result<Integer>"));
        method.setName(introspectedTable.getInsertStatementId());

        // 添加方法参数
        FullyQualifiedJavaType parameterType;
        parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        importedTypes.add(parameterType);
        Parameter parameter = new Parameter(parameterType, "record");
        parameter.addAnnotation("@RequestBody");
        parameter.addAnnotation("@Validated");
        method.addParameter(parameter); //$NON-NLS-1$

        // 添加方法注解
        method.addAnnotation("@ApiOperation(\"添加\")");
        method.addAnnotation("@PostMapping(value = \"/create\")");

        method.addBodyLine("int count = this." + toLowerCaseFirstOne(domainObjectName) + "Service." + introspectedTable.getInsertStatementId() + "(record);");
        method.addBodyLine("if (count > 0) {");
        method.addBodyLine("return Result.success(count);");
        method.addBodyLine("}");
        method.addBodyLine("return Result.failed();");

        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(
                method, controllerCompilationUnit, introspectedTable)) {
            controllerCompilationUnit.addImportedTypes(importedTypes);
            controllerCompilationUnit.addMethod(method);
        }
    }

    private void addDeleteByPrimaryKeyMethod4Controller(TopLevelClass controllerCompilationUnit, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("Result<Integer>"));
        method.setName(introspectedTable.getDeleteByPrimaryKeyStatementId());

        // 添加方法参数
        StringBuilder pathVar = addMethodParameter(introspectedTable, importedTypes, method);
        // 添加方法注解
        method.addAnnotation("@ApiOperation(\"删除\")");
        method.addAnnotation("@PostMapping(value = \"/delete/" + pathVar.toString() + "\")");

        // addBody-line,必须配置body-line,方法才有实现体,否则这个方法就是个abstract方法了

        method.addBodyLine("int count = this." + toLowerCaseFirstOne(domainObjectName) + "Service."
                + introspectedTable.getDeleteByPrimaryKeyStatementId() + "(" + getParameterStr(method) + ");");
        method.addBodyLine("if (count > 0) {\n" +
                "            return Result.success(count);\n" +
                "        }");
        method.addBodyLine("return Result.failed();");

        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(
                method, controllerCompilationUnit, introspectedTable)) {
            controllerCompilationUnit.addImportedTypes(importedTypes);
            controllerCompilationUnit.addMethod(method);
        }
    }

    private StringBuilder addMethodParameter(IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> importedTypes, Method method) {
        StringBuilder pathVar = new StringBuilder();
        List<IntrospectedColumn> introspectedColumns = introspectedTable.getPrimaryKeyColumns();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
            importedTypes.add(type);
            Parameter parameter = new Parameter(type, introspectedColumn.getJavaProperty());
            parameter.addAnnotation("@PathVariable(\"" + parameter.getName() + "\")");
            pathVar.append("{");
            pathVar.append(parameter.getName());
            pathVar.append("}");
            pathVar.append("/");
            method.addParameter(parameter);
        }

        pathVar.delete(pathVar.lastIndexOf("/"), pathVar.length());
        return pathVar;
    }

    private void setControllerField(TopLevelClass controllerCompilationUnit, IntrospectedTable introspectedTable, String servicePackage) {
        // 实体类的类名
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        // 添加Service类
        Field serviceField = new Field();
        serviceField.setName(toLowerCaseFirstOne(domainObjectName) + "Service");
        serviceField.setVisibility(JavaVisibility.PRIVATE);
        serviceField.setFinal(true);
        serviceField.setType(new FullyQualifiedJavaType(domainObjectName + "Service"));
        controllerCompilationUnit.addField(serviceField);
        controllerCompilationUnit.addImportedType(new FullyQualifiedJavaType(servicePackage + "." + domainObjectName + "Service"));
    }

    private void generateServiceFile(IntrospectedTable introspectedTable, List<GeneratedJavaFile> generatedJavaFiles, String domainObjectName, String modalFullName, String targetProject, String servicePackage, String serviceImplPackage) {
        // 生成ServiceImpl类
        generateServiceImplJavaFile(introspectedTable, generatedJavaFiles, domainObjectName, modalFullName, targetProject, servicePackage, serviceImplPackage);
    }


    /**
     * 生成ServiceImpl,Service接口的实现类
     */
    private void generateServiceImplJavaFile(IntrospectedTable introspectedTable, List<GeneratedJavaFile> generatedJavaFiles, String domainObjectName, String modalFullName, String targetProject, String servicePackage, String serviceImplPackage) {
        // ServiceImpl类
        // typeName为类的全限定类名
        String typeName = serviceImplPackage + "." + domainObjectName + "Service";
        // TopLevelClass类代表要生成的类,mybatis根据TopLevelClass类代表要生成一个类的field,method等
        TopLevelClass serviceClass = new TopLevelClass(typeName);
        // 增加类注释
        serviceClass.addJavaDocLine("/**\n" +
                " * " + introspectedTable.getRemarks() + "\n" +
                " *\n" +
                " * @author Paradise\n" +
                " */");
        serviceClass.setVisibility(JavaVisibility.PUBLIC);
        // 此类所在包
        String packageName = serviceClass.getType().getPackageName();

        // 为类添加注解
        serviceClass.addAnnotation("@Slf4j");
        serviceClass.addAnnotation("@Service");
        serviceClass.addAnnotation("@AllArgsConstructor");

        // 为类添加Field
        setServiceImplField(serviceClass, introspectedTable);

        // 为类添加方法
        setServiceImplMethod(serviceClass, introspectedTable);

        serviceClass.addImportedType(new FullyQualifiedJavaType("com.github.pagehelper.PageHelper"));
        serviceClass.addImportedType(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
        serviceClass.addImportedType(new FullyQualifiedJavaType("lombok.AllArgsConstructor"));
        serviceClass.addImportedType(new FullyQualifiedJavaType(modalFullName));
        serviceClass.addImportedType(new FullyQualifiedJavaType("java.util.List"));
        serviceClass.addImportedType(new FullyQualifiedJavaType("lombok.extern.slf4j.Slf4j"));

        GeneratedJavaFile service = new GeneratedJavaFile(serviceClass, targetProject, "UTF-8", getContext().getJavaFormatter());

        generatedJavaFiles.add(service);
    }

    /**
     * 为类添加Field
     */
    private void setServiceImplField(TopLevelClass serviceImplCompilationUnit, IntrospectedTable introspectedTable) {
        // 实体类的类名
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        // DAO类所在包的包名
        String daoTargetPackage = introspectedTable.getContext().getJavaClientGeneratorConfiguration().getTargetPackage();
        Field daoField = new Field();
        // 设置Field的注解
        daoField.setVisibility(JavaVisibility.PRIVATE);
        daoField.setFinal(true);
        // 设置Field的类型
        daoField.setType(new FullyQualifiedJavaType(domainObjectName + "Mapper"));
        // 设置Field的名称
        daoField.setName(toLowerCaseFirstOne(domainObjectName) + "Mapper");
        // 将Field添加到对应的类中
        serviceImplCompilationUnit.addField(daoField);
        // 对应的类需要import DAO类(使用全限定类名)
        serviceImplCompilationUnit.addImportedType(new FullyQualifiedJavaType(daoTargetPackage + "." + domainObjectName + "Mapper"));
    }

    /**
     * 为类添加方法
     */
    private void setServiceImplMethod(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addDeleteByPrimaryKeyMethod4Impl(topLevelClass, introspectedTable);
        addInsertMethod4Impl(topLevelClass, introspectedTable);
//        addInsertSelectiveMethod4Impl(topLevelClass, introspectedTable);
        addSelectByPrimaryKeyMethod4Impl(topLevelClass, introspectedTable);
//        addUpdateByPrimaryKeySelectiveMethod4Impl(topLevelClass, introspectedTable);
        addUpdateByPrimaryKeyMethod4Impl(topLevelClass, introspectedTable);
        // 创建selectByPage方法,对应DAO中的selectByPage方法,与 https://github.com/linweiyu21/PaginationPlugin 插件配合时使用
        addSelectByPageMethod4Impl(topLevelClass, introspectedTable);
    }

    private void addSelectByPageMethod4Impl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(new FullyQualifiedJavaType("List<" + domainObjectName + ">"));
        method.setName("selectByPage");
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "pageNum"));
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Integer"), "pageSize"));
        method.addBodyLine("PageHelper.startPage(pageNum, pageSize);");
        String example = "new " + domainObjectName + "Example()";
        method.addBodyLine("return this." + toLowerCaseFirstOne(domainObjectName) + "Mapper.selectByExample(" + example + ");");

        if (context.getPlugins()
                .clientUpdateByPrimaryKeySelectiveMethodGenerated(method,
                        topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addUpdateByPrimaryKeyMethod4Impl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        FullyQualifiedJavaType parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType());
        } else {
            parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        }
        importedTypes.add(parameterType);
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(introspectedTable
                .getUpdateByPrimaryKeyStatementId());
        method.addParameter(new Parameter(parameterType, "record"));
        method.addBodyLine("return this." + toLowerCaseFirstOne(domainObjectName) + "Mapper." + introspectedTable.getUpdateByPrimaryKeyStatementId() + "(record);");

        if (context.getPlugins()
                .clientUpdateByPrimaryKeySelectiveMethodGenerated(method,
                        topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addUpdateByPrimaryKeySelectiveMethod4Impl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        FullyQualifiedJavaType parameterType;

        if (introspectedTable.getRules().generateRecordWithBLOBsClass()) {
            parameterType = new FullyQualifiedJavaType(introspectedTable.getRecordWithBLOBsType());
        } else {
            parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        }

        importedTypes.add(parameterType);

        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(introspectedTable
                .getUpdateByPrimaryKeySelectiveStatementId());
        method.addParameter(new Parameter(parameterType, "record"));
        method.addBodyLine("return this." + toLowerCaseFirstOne(domainObjectName) + "Mapper." + introspectedTable.getUpdateByPrimaryKeySelectiveStatementId() + "(record);");

        if (context.getPlugins()
                .clientUpdateByPrimaryKeySelectiveMethodGenerated(method,
                        topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addSelectByPrimaryKeyMethod4Impl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);

        FullyQualifiedJavaType returnType = introspectedTable.getRules()
                .calculateAllFieldsClass();
        method.setReturnType(returnType);
        importedTypes.add(returnType);

        method.setName(introspectedTable.getSelectByPrimaryKeyStatementId());

        List<IntrospectedColumn> introspectedColumns = introspectedTable.getPrimaryKeyColumns();

        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            FullyQualifiedJavaType type = introspectedColumn
                    .getFullyQualifiedJavaType();
            importedTypes.add(type);
            Parameter parameter = new Parameter(type, introspectedColumn
                    .getJavaProperty());
            method.addParameter(parameter);
        }

        method.addBodyLine("return this." + toLowerCaseFirstOne(domainObjectName) + "Mapper."
                + introspectedTable.getSelectByPrimaryKeyStatementId() + "(" + getParameterStr(method) + ");");

        if (context.getPlugins().clientSelectByPrimaryKeyMethodGenerated(
                method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addInsertSelectiveMethod4Impl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getInsertSelectiveStatementId());

        FullyQualifiedJavaType parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();

        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record"));
        method.addBodyLine("return this." + toLowerCaseFirstOne(domainObjectName) + "Mapper."
                + introspectedTable.getInsertSelectiveStatementId() + "(record);");

        if (context.getPlugins().clientInsertSelectiveMethodGenerated(
                method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private void addInsertMethod4Impl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();

        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();

        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getInsertStatementId());

        FullyQualifiedJavaType parameterType;
        parameterType = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());

        importedTypes.add(parameterType);
        method.addParameter(new Parameter(parameterType, "record"));

        method.addBodyLine("return this." + toLowerCaseFirstOne(domainObjectName) + "Mapper." + introspectedTable.getInsertStatementId() + "(record);");

        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    public void addDeleteByPrimaryKeyMethod4Impl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
        method.setName(introspectedTable.getDeleteByPrimaryKeyStatementId());
        List<IntrospectedColumn> introspectedColumns = introspectedTable.getPrimaryKeyColumns();
        for (IntrospectedColumn introspectedColumn : introspectedColumns) {
            FullyQualifiedJavaType type = introspectedColumn
                    .getFullyQualifiedJavaType();
            importedTypes.add(type);
            Parameter parameter = new Parameter(type, introspectedColumn
                    .getJavaProperty());
            method.addParameter(parameter);
        }
        String sb = getParameterStr(method);
        method.addBodyLine("return this." + toLowerCaseFirstOne(domainObjectName) + "Mapper." + introspectedTable.getDeleteByPrimaryKeyStatementId() + "(" + sb.toString() + ");");

        if (context.getPlugins().clientDeleteByPrimaryKeyMethodGenerated(
                method, topLevelClass, introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }

    private String getParameterStr(Method method) {
        List<Parameter> parameters = method.getParameters();
        StringBuilder sb = new StringBuilder();
        for (Parameter parameter : parameters) {
            sb.append(parameter.getName());
            sb.append(",");
        }
        sb.delete(sb.lastIndexOf(","), sb.length());
        return sb.toString();
    }

    private static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toLowerCase(s.charAt(0)) + s.substring(1);
        }
    }


}
