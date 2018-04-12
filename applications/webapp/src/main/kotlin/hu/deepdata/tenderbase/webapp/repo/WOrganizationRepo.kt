package hu.deepdata.tenderbase.webapp.repo

import hu.deepdata.tenderbase.webapp.model.*
import org.springframework.data.jpa.repository.*
import org.springframework.stereotype.*

/**
 * @author Zsolt Jurányi
 */
@Repository
interface WOrganizationRepo : JpaRepository<WOrganization, String>