package com.sagardhone.library_management

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["com.sagardhone"])
@EntityScan(basePackages = ["com.sagardhone"])
@EnableJpaRepositories(basePackages = ["com.sagardhone"])
class LibraryManagementApplication


fun main(args: Array<String>) {
	runApplication<LibraryManagementApplication>(*args)
}
