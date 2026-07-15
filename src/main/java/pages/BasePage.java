package pages;

import utilities.*;

public class BasePage {

    protected ElementActions element = new ElementActions();
    protected WaitUtils wait = new WaitUtils();
    protected MouseActions mouse = new MouseActions();
    protected KeyboardActions keyboard = new KeyboardActions();
    protected JavaScriptUtils js = new JavaScriptUtils();
    protected DropdownUtils dropdown = new DropdownUtils();
    protected AlertUtils alert = new AlertUtils();
    protected FrameUtils frame = new FrameUtils();
    protected WindowUtils window = new WindowUtils();
    protected NavigationUtils navigation = new NavigationUtils();
    protected BrowserUtils browser = new BrowserUtils();
    protected ScreenshotUtils screenshot = new ScreenshotUtils();
}