package com.github.allure.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.allure.steps.WebSteps;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class RepositoryNavTest extends TestBase {
    private static final String REPOSITORY = "Metaf-x/selenide-tests-sandbox";

    @Test
    void selenideCheckIfTabIssueExistsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("/");
        $(".header-search-button").click();
        $("#query-builder-test")
                .setValue(REPOSITORY)
                .submit();
        $(linkText(REPOSITORY)).click();
        $("#issues-tab").click();
        $("#repo-content-turbo-frame .blankslate")
                .shouldHave(text("Welcome to issues!"));
    }

    @Test
    void lambdaStepsCheckIfTabIssueExistsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Open main page", () -> {
            open("/");
        });
        step("Find repository " + REPOSITORY, () -> {
            $(".header-search-button").click();
            $("#query-builder-test")
                    .setValue(REPOSITORY)
                    .submit();
        });
        step("Click on repository link", () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Click on Issues tab", () -> {
            $("#issues-tab").click();
        });
        step("Check welcome message on Issue page", () -> {
            $("#repo-content-turbo-frame .blankslate")
                    .shouldHave(text("Welcome to issues!"));
        });
    }

    @Test
    void annotatedStepsCheckIfTabIssueExistsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.findRepository(REPOSITORY);
        steps.goToRepository(REPOSITORY);
        steps.goToIssuesTab();
        steps.checkEmptyIssuesTabMessage();
    }
}
