package com.mirego.trikot.analytics

import com.mirego.trikot.foundation.CommonJSExport

@CommonJSExport
class EmptyAnalyticsService : AnalyticsService {
    override val name: String = "ANALYTICS SERVICE NOT CONFIGURED"

    override var enabled = false

    override fun identifyUser(userId: String, properties: AnalyticsPropertiesType) {
    }

    override fun logout() {
    }

    override fun incrementUserProperties(incrementalProperties: AnalyticsIncrementalProperties) {
    }

    override fun setSuperProperties(properties: AnalyticsPropertiesType) {
    }

    override fun setUserProperties(properties: AnalyticsPropertiesType) {
    }

    override fun unsetSuperProperties(propertyNames: List<String>) {
    }

    override fun unsetAllSuperProperties() {
    }

    override fun trackEvent(event: AnalyticsEvent, properties: AnalyticsPropertiesType) {
    }
}
