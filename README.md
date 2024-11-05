[![UI test execution](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/ci.yml)

# ABOUT

Automated end-to-end testing framework built with Playwright and Java, tailored for any website. Designed
for scalability and maintainability, this framework covers critical test scenarios with robust assertions and clean,
modular code. Perfect for those looking to speed up their test automation journey or enhance their Playwright skills!

# STEPS FOR THE TEST EXECUTION IN LOCAL

1. `git clone https://github.com/iamcharankumar/playwright_test_framework.git`
2. `cd playwright_test_framework`
3. `git pull`
4. `mvn clean test -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dtestng.parallel=methods
   -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

**SUPPORTED BROWSERS**

- Chrome `-Dbrowser=chrome` (default value is `chrome`)
- Firefox `-Drunmode=firefox`
- Microsoft Edge `-Drunmode=msedge`

**SUPPORTED RUN MODES**

- local `-Drunmode=local` (default value is `local`)
- headless `-Drunmode=headless`

# REPORTPORTAL INTEGRATION

- To integrate your test reports with the open source tool - [**Reportportal**](https://reportportal.io/docs/), please
  refer to
  this [section](https://github.com/iamcharankumar/web_test_framework?tab=readme-ov-file#steps-for-integrating-testng--report-portal)
  in my java-selenium framework repo for installation as well as the integration.

# INTEGRATE DISCORD MESSAGE SERVICE

- To send test reports from Reportportal to any Discord Message Channel, please refer to
  this [section](https://github.com/iamcharankumar/web_test_framework?tab=readme-ov-file#steps-for-integrating-test-reports-report-portal-url-with-discord-message-service)
  and all the classes in
  this [package](https://github.com/iamcharankumar/web_test_framework/tree/master/src/main/java/io/saucelabs/portal/qa/services/discord)
  of the same java-selenium framework repo.

# EXTRAS

- Below is the maven commands combo that will help you to cover all the supported browsers and runmodes.
- These maven commands (no testng.xml required) are executed with the respective groups and thread counts.
  The listeners [screenshot, retry, etc] are configured in "pom.xml" under "< property >" tag.

#### CHROME LOCAL & HEADLESS

`mvn clean test -Drunmode=local -Dbrowser=chrome -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

`mvn clean test -Drunmode=headless -Dbrowser=chrome -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

### MS EDGE LOCAL & HEADLESS

`mvn clean test -Drunmode=local -Dbrowser=msedge -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

`mvn clean test -Drunmode=headless -Dbrowser=msedge -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

### WEBKIT LOCAL & HEADLESS

`mvn clean test -Drunmode=local -Dbrowser=webkit -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E 
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

`mvn clean test -Drunmode=headless -Dbrowser=webkit -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E 
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

### FIREFOX LOCAL & HEADLESS

`mvn clean test -Drunmode=local -Dbrowser=firefox -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

`mvn clean test -Drunmode=headless -Dbrowser=firefox -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`

### DEFAULT RUN

`mvn clean test -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E
-Dtestng.parallel=methods -DthreadPoolSize=3 -Ddataproviderthreadcount=3`
