Dưới đây là một bài test cho **ứng dụng E-Commerce** có nhiều thao tác phức tạp hơn.

---

# **📌 Kịch Bản Test**

Giả sử chúng ta có một ứng dụng **E-Commerce** chạy trên `http://localhost:3000`, với các chức năng:

1. **Đăng nhập** vào tài khoản người dùng.
2. **Tìm kiếm sản phẩm**.
3. **Thêm sản phẩm vào giỏ hàng**.
4. **Thanh toán đơn hàng**.

---

# **🛠 Cấu Trúc Dự Án**

```
selenium-ecommerce/
│── package.json
│── test/
│   ├── ecommerce.test.js
│── node_modules/
```

---

# **🛠 Cài Đặt Selenium WebDriver và Mocha**

```sh
npm init -y
npm install selenium-webdriver mocha chai chromedriver
```

---

# **📌 Viết Bài Test**

Tạo file `test/ecommerce.test.js` với nội dung sau:

```javascript
const { Builder, By, Key, until } = require("selenium-webdriver");
const { expect } = require("chai");

describe("E-Commerce App Tests", function () {
  let driver;

  // Mở trình duyệt trước khi chạy test
  before(async function () {
    driver = await new Builder().forBrowser("chrome").build();
    await driver.get("http://localhost:3000");
  });

  // Đóng trình duyệt sau khi hoàn thành test
  after(async function () {
    await driver.quit();
  });

  // Test: Đăng nhập vào hệ thống
  it("should log in successfully", async function () {
    await driver
      .findElement(By.id("login-email"))
      .sendKeys("testuser@example.com");
    await driver
      .findElement(By.id("login-password"))
      .sendKeys("password123", Key.RETURN);

    // Chờ điều hướng sau khi đăng nhập
    await driver.wait(until.urlContains("/dashboard"), 5000);

    let welcomeMessage = await driver
      .findElement(By.id("welcome-msg"))
      .getText();
    expect(welcomeMessage).to.include("Welcome, Test User");
  });

  // Test: Tìm kiếm sản phẩm
  it("should search for a product", async function () {
    let searchBox = await driver.findElement(By.id("search-box"));
    await searchBox.sendKeys("Laptop", Key.RETURN);

    // Chờ kết quả hiển thị
    let firstProduct = await driver.wait(
      until.elementLocated(By.css(".product-item")),
      5000
    );

    let productText = await firstProduct.getText();
    expect(productText.toLowerCase()).to.include("laptop");
  });

  // Test: Thêm sản phẩm vào giỏ hàng
  it("should add product to cart", async function () {
    let addToCartButton = await driver.findElement(By.css(".add-to-cart"));
    await addToCartButton.click();

    // Kiểm tra xem sản phẩm đã được thêm vào giỏ hàng chưa
    let cartCount = await driver.findElement(By.id("cart-count")).getText();
    expect(cartCount).to.equal("1");
  });

  // Test: Thanh toán đơn hàng
  it("should complete checkout process", async function () {
    await driver.findElement(By.id("cart")).click();
    await driver.findElement(By.id("checkout-button")).click();

    // Điền thông tin thanh toán
    await driver.findElement(By.id("card-number")).sendKeys("4111111111111111");
    await driver.findElement(By.id("expiration-date")).sendKeys("12/25");
    await driver.findElement(By.id("cvv")).sendKeys("123");

    // Xác nhận thanh toán
    await driver.findElement(By.id("confirm-payment")).click();

    // Kiểm tra xác nhận đơn hàng
    let confirmationMsg = await driver.wait(
      until.elementLocated(By.id("order-confirmation")),
      5000
    );

    let confirmationText = await confirmationMsg.getText();
    expect(confirmationText).to.include("Thank you for your purchase");
  });
});
```

---

# **📌 Chạy Bài Test**

```sh
npx mocha test/ecommerce.test.js --timeout 10000
```

---

# **📌 Giải Thích Chi Tiết**

1. **Đăng nhập vào hệ thống**:
   - Nhập email + mật khẩu.
   - Nhấn Enter.
   - Kiểm tra URL có chứa `/dashboard` không.
   - Kiểm tra tin nhắn chào mừng `"Welcome, Test User"`.
2. **Tìm kiếm sản phẩm**:
   - Nhập `"Laptop"` vào ô tìm kiếm.
   - Nhấn Enter.
   - Kiểm tra danh sách sản phẩm có hiển thị sản phẩm `"Laptop"` không.
3. **Thêm sản phẩm vào giỏ hàng**:

   - Nhấn vào nút "Thêm vào giỏ hàng".
   - Kiểm tra số lượng sản phẩm trong giỏ hàng (`cart-count`).

4. **Thanh toán đơn hàng**:
   - Nhấn vào giỏ hàng.
   - Điền thông tin thẻ.
   - Nhấn `"Xác nhận thanh toán"`.
   - Kiểm tra tin nhắn `"Thank you for your purchase"`.
