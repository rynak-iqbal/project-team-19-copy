package com.example.demo.cucumber

import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite
import io.cucumber.core.options.Constants

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("contracts")  // Changed from "features"
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "com.example.demo.cucumber")
class CucumberTest