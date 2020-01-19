package com.pinko

import com.github.javaparser.ast.CompilationUnit
import com.github.javaparser.ast.Modifier
import com.github.javaparser.ast.body.EnumDeclaration
import com.github.javaparser.ast.expr.AssignExpr
import com.github.javaparser.ast.expr.FieldAccessExpr
import com.github.javaparser.ast.expr.NameExpr
import com.github.javaparser.ast.expr.ThisExpr
import com.github.javaparser.ast.stmt.BlockStmt
import com.github.javaparser.ast.stmt.ExpressionStmt
import java.io.File

/**
 * Created by PP on 19. 01. 20.
 */
class DataClass(val javaFile: String) {

    var data = CompilationUnit()
    private var dataEnum: EnumDeclaration = data.addEnum("Data")

    fun createEnumDefinition(fields: Array<String>) {
        val constructor = dataEnum.addConstructor()
        val body = BlockStmt()

        for (field  in fields) {
            dataEnum.addField(
                "String",
                field,
                Modifier.Keyword.PRIVATE,
                Modifier.Keyword.FINAL
            )

//            datassEnum.addField(
//                "boolean",
//                "onDemand",
//                Modifier.Keyword.PRIVATE,
//                Modifier.Keyword.FINAL
//            )

            constructor.addParameter("String", field)
            body.addStatement(
                ExpressionStmt(
                    AssignExpr(
                        FieldAccessExpr(ThisExpr(), field),
                        NameExpr(field),
                        AssignExpr.Operator.ASSIGN
                    )
                )
            )
        }

        constructor.body = body
    }

    fun addEnumConstant(name: String, values: Array<String>) {
        val enumConstantDeclaration = dataEnum.addEnumConstant(name)
        for (value in values) {
            enumConstantDeclaration.addArgument("\"$value \"")
        }
    }

    fun save() {
        val data = dataEnum.toString()
        println(data)
        File(javaFile).writeText(data)
    }
}