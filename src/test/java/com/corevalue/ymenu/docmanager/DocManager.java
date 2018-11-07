package com.corevalue.ymenu.docmanager;

import com.corevalue.constant.docmanager.DM;
import com.corevalue.driver.BrowserMap;
import com.corevalue.driver.TestGroup;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Slf4j
public class DocManager {
    private TestGroup testGroup;

    public DocManager(TestGroup testGroup) {
        this.testGroup = testGroup;
    }

    /**
     * This method open all documents on each categories
     *
     * NOTE: was used _for loop_ instead of streams for the following reason:
     * --> when we open our document the DOM changes and selenium throws the next exception:
     *
     *          org.openqa.selenium.StaleElementReferenceException:
     *          stale element reference: element is not attached to the page document
     *
     * So we cannot use enhanced loop or streams.
     * To prevent this we should use new search of our target elements as we do that using _for loop_ and indexes.
     *
     * For more details see: https://www.seleniumhq.org/exceptions/stale_element_reference.jsp
     */
    public void openEachDocuments() {
        int docsTreeSize = getDocsTree().size();
        for (int treeIndex = 0; treeIndex < docsTreeSize; treeIndex++) {
            int menusSize = getMenus(treeIndex).size();
            for (int menuIndex = 0; menuIndex < menusSize; menuIndex++) {
                getMenus(treeIndex).get(menuIndex).click();
                int submenusSize = getSubmenus(treeIndex, menuIndex).size();
                for (int submenuIndex = 0; submenuIndex < submenusSize; submenuIndex++) {
                    getSubmenus(treeIndex, menuIndex).get(submenuIndex).click();
                    int docsSize = getDocs(treeIndex, menuIndex, submenuIndex).size();
                    for(int docIndex = 0; docIndex < docsSize; docIndex++ ) {
                        try {
                            getDocs(treeIndex, menuIndex, submenuIndex).get(docIndex).click();
                        } catch (Exception e) {
                            setLog(treeIndex, menuIndex, submenuIndex, docIndex);
                        }
                    }
                }
            }
        }
    }

//// NOTE: This one doesn't works due to org.openqa.selenium.StaleElementReferenceException
//    public void openEachDocuments() {
//        driver().findElements(DM.ID_DOC_TREE.getSelector())
//                .forEach(block -> block.findElements(By.cssSelector("#browser > li"))
//                        .forEach(menu -> {
//                            menu.click();
//                            menu.findElements(By.cssSelector("#browser > li > ul > li"))
//                                    .forEach(submenu -> {
//                                        submenu.click();
//                                        submenu.findElements(By.cssSelector("#browser > li > ul > li > ul > li"))
//                                                .forEach(WebElement::click);
//                                    });
//                        })
//                );
//    }

    //region HELPER METHODS
    private List<WebElement> getDocsTree() {
        return driver().findElements(DM.DOC_TREE_ID.getSelector());
    }

    private List<WebElement> getMenus(int treeIndex) {
        return getDocsTree().get(treeIndex).findElements(DM.MENU_SELECTOR.getSelector());
    }

    private List<WebElement> getSubmenus(int treeIndex, int menuIndex) {
        return getMenus(treeIndex).get(menuIndex).findElements(DM.SUBMENU_SELECTOR.getSelector());
    }

    private List<WebElement> getDocs(int treeIndex, int menuIndex, int submenuIndex) {
        return getSubmenus(treeIndex, menuIndex).get(submenuIndex).findElements(DM.DOC_SELECTOR.getSelector());
    }

    private void setLog(int treeIndex, int menuIndex, int submenuIndex, int docIndex) {
        String treeSelector = String.format("#docTree:nth-child(%s) > ul:nth-child(1) > li:nth-child(1)",
                treeIndex+1);
        String menuSelector = String.format("#docTree:nth-child(%s) #browser > li:nth-child(%s) > span",
                treeIndex+1, menuIndex+1);
        String submenuSelector = String.format("#docTree:nth-child(%s) #browser > li:nth-child(%s) > ul > li:nth-child(%s) > span",
                treeIndex+1, menuIndex+1, submenuIndex+1);
        String docSelector = String.format("#docTree:nth-child(%s) #browser > li:nth-child(%s) > ul > li:nth-child(%s) > ul > li:nth-child(%s) > span",
                treeIndex+1, menuIndex+1, submenuIndex+1, docIndex+1);

        String treeName = driver().findElement(By.cssSelector(treeSelector)).getText().trim();
        String menuName = driver().findElement(By.cssSelector(menuSelector)).getText().trim();
        String submenuName = driver().findElement(By.cssSelector(submenuSelector)).getText().trim();
        String docName = driver().findElement(By.cssSelector(docSelector)).getText().trim();

        log.error(String.format("Cannot allocate document:\n\t[%s]\n\t\t-- %s\n\t\t\t-- %s\n\t\t\t\t--> %s",
                treeName, menuName, submenuName, docName));
    }
    //endregion

    private WebDriver driver() {
        return BrowserMap.INSTANCE.getDrivers().get(testGroup).getDriver();
    }
}
