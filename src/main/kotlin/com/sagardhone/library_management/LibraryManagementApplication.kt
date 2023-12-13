package com.sagardhone.library_management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.sagardhone"])
class LibraryManagementApplication

fun main(args: Array<String>) {
	runApplication<LibraryManagementApplication>(*args)
}
