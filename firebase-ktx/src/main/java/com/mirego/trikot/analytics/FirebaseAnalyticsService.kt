package com.mirego.trikot.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.mirego.trikot.analytics.AnalyticsEvent
import com.mirego.trikot.analytics.AnalyticsPropertiesType
import com.mirego.trikot.analytics.AnalyticsService

class FirebaseAnalyticsService(context: Context) : AnalyticsService {
    private var firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    override val name: String = "FirebaseAnalytics"
    private var superProperties = emptyMap<String, Any>()

    override fun identifyUser(userId: String, properties: AnalyticsPropertiesType) {
        firebaseAnalytics.setUserId(userId)
        properties.forEach {
            firebaseAnalytics.setUserProperty(it.key, it.value.toString())
        }
    }

    //This functionality isn't supported with Firebase Analytics
    override fun incrementUserProperties(incrementalProperties: AnalyticsIncrementalProperties) = Unit

    override fun logout() {
        firebaseAnalytics.setUserId(null)
    }

    override fun setSuperProperties(properties: AnalyticsPropertiesType) {
        superProperties = superProperties + properties
    }

    override fun unsetSuperProperties(propertyNames: List<String>) {
        superProperties = superProperties.entries
            .filter { !propertyNames.contains(it.key) }
            .fold(emptyMap()) { acc, value ->
                acc + value.toPair()
            }
    }

    override fun unsetAllSuperProperties() {
        superProperties = emptyMap()
    }

    override fun trackEvent(event: AnalyticsEvent, properties: AnalyticsPropertiesType) {
        val allProperties = properties + superProperties
        val bundle = Bundle()
        allProperties.forEach {
            bundle.putString(it.key, it.value.toString())
        }
        firebaseAnalytics.logEvent(event.name, bundle)
    }
}
