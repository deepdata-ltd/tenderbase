package hu.deepdata.tenderbase.webapp.repo

import hu.deepdata.tenderbase.webapp.model.*
import org.springframework.data.jpa.repository.*

/**
 * @author Zsolt Jurányi
 */
interface WCacheRepo : JpaRepository<WCache, String>