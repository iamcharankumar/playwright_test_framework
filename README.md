![Playwright](https://img.shields.io/badge/-playwright-%232EAD33?style=for-the-badge&logo=playwright&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)

[![UI test execution](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/ci.yml)
[![CodeQL](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/codeql.yml/badge.svg)](https://github.com/iamcharankumar/playwright_test_framework/actions/workflows/codeql.yml)
[![codecov](https://codecov.io/gh/iamcharankumar/playwright_test_framework/branch/master/graph/badge.svg?token=HT5GS0RC0C)](https://codecov.io/gh/iamcharankumar/playwright_test_framework)

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
- In this framework, Discord has been integrated via Github actions. Refer final result in the image below.
  <img width="1657" alt="Discord_Report_Message" src="https://github.com/user-attachments/assets/0f808ece-bcb3-486a-9e70-620dcb7b0f95">

# CODE COVERAGE [CODECOV TOOL]

- The code coverage is leveraged via Github Actions integrating codecov tool.
- The latest code coverage for this project is
  available [here](https://app.codecov.io/gh/iamcharankumar/playwright_test_framework).
- The code coverage grid:
  ![codecov](https://codecov.io/gh/iamcharankumar/playwright_test_framework/graphs/tree.svg?token=HT5GS0RC0C)

# EXTRAS

- Below is the maven commands combo that will help you to cover all the supported browsers and runmodes.
- These maven commands (no testng.xml required) are executed with the respective groups and thread counts.
  The listeners [screenshot, retry, etc] are configured in "pom.xml" under "< property >" tag.

#### CHROME LOCAL & HEADLESS

`mvn clean test -Drunmode=local -Dbrowser=chrome -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

`mvn clean test -Drunmode=headless -Dbrowser=chrome -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

### MS EDGE LOCAL & HEADLESS

`mvn clean test -Drunmode=local -Dbrowser=msedge -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

`mvn clean test -Drunmode=headless -Dbrowser=msedge -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

### WEBKIT LOCAL & HEADLESS

`mvn clean test -Drunmode=local -Dbrowser=webkit -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

`mvn clean test -Drunmode=headless -Dbrowser=webkit -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

### FIREFOX LOCAL & HEADLESS

`mvn clean test -Drunmode=local -Dbrowser=firefox -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

`mvn clean test -Drunmode=headless -Dbrowser=firefox -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`

### DEFAULT RUN

`mvn clean test -Dgroups=SWAG_LABS_SMOKE,SWAG_LABS_REGRESSION,SWAG_LABS_E2E -Dthreads=3 -Ddataproviderthreadcount=3`


## Star History

[![Star History Chart](https://api.star-history.com/svg?repos=iamcharankumar/playwright_test_framework&type=Date)](https://star-history.com/#iamcharankumar/playwright_test_framework&Date)
