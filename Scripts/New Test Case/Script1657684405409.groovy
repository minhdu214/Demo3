import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

WebUI.openBrowser('')

WebUI.maximizeWindow()

'Read row for OktaPreview URL'
URLRow = CustomKeywords.'Get Value From Data File'('Data Files/Application URL', 'oktapreview', 'App Name')

'Read Application URL from datafile'
ApplicationURL = findTestData('Application URL').getValue('URL', URLRow)

not_run: WebUI.navigateToUrl(findTestData('Application URL').getValue(2, 1))

WebUI.navigateToUrl(ApplicationURL)

WebUI.setText(findTestObject('Generic/LogInToSFDC/Page_oktapreview/input_username'), LogInUserId)

WebUI.setText(findTestObject('Generic/LogInToSFDC/Page_oktapreview/input_password'), LogInPassword)

'Login to oktapreview'
WebUI.click(findTestObject('Generic/LogInToSFDC/Page_oktapreview/input_okta-signin-submit'))

WebUI.verifyElementPresent(findTestObject('Object Repository/Okta/Okta_UserHome_CadenceLoga'), 180, FailureHandling.STOP_ON_FAILURE)

WebUI.delay(GlobalVariable.isElementPresentLogin)

'Read Sandbox environment from datafile'
SBXEnv = findTestData('Application URL').getValue('Environment', URLRow)

SelectedEnvironment = (('//span[text()=\'Salesforce.com - ' + SBXEnv) + '\']')

WebUI.click(findTestObject('CustomObjectFolder/CustomObject', [('xpath') : SelectedEnvironment]))

WebUI.delay(GlobalVariable.NormaDropDown)

WebUI.delay(5)

WebUI.switchToWindowIndex(1)

WebUI.delay(GlobalVariable.NormalPageLoad)

WebUI.acceptAlert()

