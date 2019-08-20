package com.viewics;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.viewics.common.controls.BaseTest;

/**
 * @author sheetal.mgaikwad@gmail.com
 *
 */
public class ShoppingAtMyntra extends BaseTest {

	
	public static final int PAGELOAD_TIME=30;
	public static final String UNAME="test.user.myntra.shopping@gmail.com";
	public static final String PWD="Ruhi@123";
	public static final String LOGIN_MODULE = "login";

	@Test(description="search expected item")
	public void searchItem() throws Exception {
		Assert.assertTrue(myntraController.homePage().isSearchDisplayed(),"Search box is not displayed");
		Assert.assertTrue(myntraController.homePage().isSearchEnabled(),"Serach box is not enabled");
		myntraController.homePage().enterSearchText("Lavie women bags");
		Reporter.log("Searching item \"Lavie women bags\".", true);
	}

	@Test(dependsOnMethods="searchItem", description="click on wish list for slected item")
	public void addItemToWishList() throws Exception {
		boolean itemListed = myntraController.homePage().isItemListed("Lavie Yellow Solid Sling Bag");
		Assert.assertTrue(itemListed,"Item is not found");
		
		Reporter.log("Your searched item \"Lavie women bags\" found successfully.", true);
		myntraController.homePage().addToWishlist("Lavie Yellow Solid Sling Bag","lavie-yellow-solid-sling-bag");
		
	}
	
	@Test(dependsOnMethods="addItemToWishList", description="login")
	public void login() throws Exception {
		
		Assert.assertTrue(myntraController.loginPage().isLoginPageLoaded(),"Failed: page not loaded");
		myntraController.loginPage().enterUsername(UNAME);
		myntraController.loginPage().enterPassword(PWD);
		myntraController.loginPage().clickSignIn();
		
		myntraController.loginPage().goToProfile();
		Assert.assertEquals(myntraController.loginPage().isUserLoggedIn(),UNAME,"User login failed with correct username");
	
		Reporter.log("You have successfully logged in to myntra.",true);
	}
	
	@Test(dependsOnMethods="login", description="again added item to wishlist after login and check item in wishlist")
	public void additemToWishListAfterLogin() throws Exception {
		//re-using the method
		addItemToWishList();
		Assert.assertEquals(myntraController.homePage().verifyNotify(),"Added to wishlist","Unable to add to wishlist as notify text doesnt match");
		
		myntraController.homePage().goToWishlist();
		
		Reporter.log("Successfully moved item to Wishlist.",true);
	}

	@Test(dependsOnMethods="additemToWishListAfterLogin", description="check item in wishlist and added item to bag/cart")
	public void checkwishlistItem() throws Exception {
		Assert.assertTrue(myntraController.wishListPage().isItemListed(),"Selected item not present in wishlist");
		
		myntraController.wishListPage().moveToBag();
		myntraController.wishListPage().selectItemSize();
		myntraController.wishListPage().clickDone();
		
		Assert.assertTrue(myntraController.wishListPage().isWishlistEmpty(),"failed to added item in bag ");
		Reporter.log("Successfully moved item to shopping bag.",true);
	}
	
	@Test(dependsOnMethods="checkwishlistItem", description="check item in cart/bag")
	public void checkItemInCart() throws Exception {
		myntraController.cartItemPage().goToCart();
		Assert.assertTrue(myntraController.cartItemPage().isItemListedInCart(),"Your item is not added to bag/cart to checkout");
		
		Reporter.log("Yayy!! Your item has been added to cart succesfully.",true);
	}
	
}
