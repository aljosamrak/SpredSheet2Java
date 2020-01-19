package com.pinko

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileInputStream
import java.io.IOException

object Main {

    private const val DATA_EXCEL_FILE = "Test Data/data.xlsx"
    private const val DATA_JAVA_FILE = "Test Data/Data.java"

    @Throws(IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val myFile = File(DATA_EXCEL_FILE )
        val fis = FileInputStream(myFile)
        // Finds the workbook instance for XLSX file
        val myWorkBook = XSSFWorkbook(fis)
        // Return first sheet from the XLSX workbook
        val mySheet = myWorkBook.getSheetAt(0)
        // Get iterator to all the rows in current sheet

        val dataClass = DataClass(DATA_JAVA_FILE)

        val iterator = mySheet.rowIterator()

        dataClass.createEnumDefinition(iterator.next().map { it.stringCellValue }.toTypedArray())

        for (row in iterator) {
            dataClass.addEnumConstant(row.getCell(0).stringCellValue, row.map { it.stringCellValue }.toTypedArray())
        }
        dataClass.save()

        // Compile generated java class
        compileClass(dataClass.javaFile)

        // Generate JAR from object flie
        generateJar(dataClass.javaFile)

        // Upload JAR to local Maven
        createMvnArtefact(dataClass.javaFile)
    }

    private fun compileClass(file: String) {
        ProcessBuilder("javac", file).start()
    }

    private fun generateJar(file: String) {
        ProcessBuilder("jar", "cvf",
            file.replace("java", "jar"),
            file.replace("java", "class"))
            .start()
    }

    private fun createMvnArtefact(file: String) {
        ProcessBuilder("mvn", "install:install-file", "-Dfile=" + file.replace("java", "jar"),
            "-DgroupId=com.pinko",
            "-DartifactId=Data",
            "-Dversion=0.1.0-SNAPSHOT",
            "-Dpackaging=jar",
            "-DcreateChecksum=true").start()
    }
}