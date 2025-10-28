package com.example.demo.cucumber.steps

import io.cucumber.java.en.Given
import io.cucumber.java.en.When
import io.cucumber.java.en.Then

class SampleSteps {
    @Given("I have a working Cucumber setup")
    fun setup() {
        println("Setup complete")
    }

    @When("I run my tests")
    fun runTests() {
        println("Running tests")
    }

    @Then("everything should work")
    fun verify() {
        println("Verification complete")
    }
}