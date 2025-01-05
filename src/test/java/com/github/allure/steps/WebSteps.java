package com.github.allure.steps;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {
    @Step("Open main page")
    public void openMainPage() {
        open("/");
    }

    @Step("Find repository {repo}")
    public void findRepository(String repo) {
        $(".header-search-button").click();
        $("#query-builder-test")
                .setValue(repo)
                .submit();
    }

    @Step("Click on repository link")
    public void goToRepository(String repo) {
        $(linkText(repo)).click();
    }

    @Step("Click on Issues tab")
    public void goToIssuesTab() {
        $("#issues-tab").click();
    }

    @Step("Check welcome message on Issue page")
    public void checkEmptyIssuesTabMessage() {
        $("#repo-content-turbo-frame .blankslate")
                .shouldHave(text("Welcome to issues!"));
    }
}
