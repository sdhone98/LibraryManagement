package com.sagardhone.library_management.bo_ok

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Bo_ok, String>