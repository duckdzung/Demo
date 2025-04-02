Tôi sẽ đi sâu vào từng phần của Selenium với JavaScript, cung cấp đầy đủ thông tin, ví dụ và giải thích chi tiết nhất.

---

# **Phần 1: Giới Thiệu Về Selenium WebDriver**

## **1. Selenium Là Gì?**

Selenium là một bộ công cụ mã nguồn mở được sử dụng để tự động hóa kiểm thử ứng dụng web trên các trình duyệt khác nhau. Nó hỗ trợ nhiều ngôn ngữ lập trình, bao gồm:

- **Java**
- **Python**
- **C#**
- **JavaScript** (Chủ đề chính của chúng ta)

Selenium WebDriver giúp điều khiển trình duyệt giống như một người dùng thực sự bằng cách tương tác với các phần tử trên trang web.

## **2. Các Thành Phần Chính Của Selenium**

Selenium bao gồm 3 thành phần chính:

### **a. Selenium WebDriver**

- Cung cấp API để điều khiển trình duyệt web.
- Không cần cài đặt một máy chủ trung gian như Selenium RC.
- Hỗ trợ nhiều trình duyệt: Chrome, Firefox, Edge, Safari.

### **b. Selenium IDE**

- Là một extension của Chrome/Firefox để ghi lại các thao tác trên trình duyệt.
- Không cần viết mã, phù hợp với những người mới bắt đầu.

### **c. Selenium Grid**

- Hỗ trợ chạy kiểm thử song song trên nhiều trình duyệt và thiết bị khác nhau.
- Dùng để tối ưu hiệu suất kiểm thử.

## **3. Selenium WebDriver Hoạt Động Như Thế Nào?**

WebDriver hoạt động bằng cách gửi lệnh đến trình duyệt và nhận phản hồi từ trình duyệt.  
Quá trình này bao gồm:

1. **Lập trình viên viết mã** sử dụng Selenium WebDriver API.
2. **WebDriver gửi lệnh đến trình duyệt** thông qua trình điều khiển (driver).
3. **Trình duyệt thực hiện thao tác** và gửi phản hồi về WebDriver.
4. **WebDriver trả về kết quả** cho chương trình test.

### **Lưu đồ hoạt động của Selenium WebDriver:**

```plaintext
(Test Script) --> (WebDriver API) --> (Browser Driver) --> (Browser)
```

---

# **Phần 2: Cài Đặt Môi Trường Selenium với JavaScript**

## **1. Cài Đặt Node.js**

Selenium với JavaScript yêu cầu **Node.js** vì nó chạy trên môi trường Node.

- Tải về từ [https://nodejs.org](https://nodejs.org)
- Kiểm tra phiên bản sau khi cài đặt:
  ```sh
  node -v
  npm -v
  ```

## **2. Tạo Dự Án Node.js**

Tạo một thư mục mới:

```sh
mkdir selenium-js && cd selenium-js
npm init -y
```

Tập tin `package.json` sẽ được tạo.

## **3. Cài Đặt Selenium WebDriver**

Cài Selenium WebDriver bằng npm:

```sh
npm install selenium-webdriver
```

Cài đặt trình điều khiển ChromeDriver:

```sh
npm install chromedriver
```

Nếu sử dụng Firefox, cài `geckodriver`:

```sh
npm install geckodriver
```

---

# **Phần 3: Viết Chương Trình Đầu Tiên Với Selenium**

## **1. Viết Script Selenium với JavaScript**

Tạo file `test.js` và thêm đoạn mã sau:

```javascript
const { Builder, By, Key, until } = require("selenium-webdriver");

async function test() {
  let driver = await new Builder().forBrowser("chrome").build();

  try {
    await driver.get("https://www.google.com");
    let searchBox = await driver.findElement(By.name("q"));
    await searchBox.sendKeys("Selenium JavaScript", Key.RETURN);
    await driver.wait(until.titleContains("Selenium JavaScript"), 5000);
  } finally {
    await driver.quit();
  }
}

test();
```

## **2. Chạy Chương Trình**

Chạy file bằng lệnh:

```sh
node test.js
```

Trình duyệt Chrome sẽ mở, nhập từ khóa vào Google và hiển thị kết quả.

---

# **Phần 4: Tìm Hiểu Các Thao Tác Chính Trong Selenium**

## **1. Các Cách Tìm Phần Tử (Locators)**

Trong Selenium, có nhiều cách tìm phần tử trên trang web:

| Locator Type | Cách Viết                               |
| ------------ | --------------------------------------- |
| ID           | `By.id("element-id")`                   |
| Name         | `By.name("element-name")`               |
| Class        | `By.className("class-name")`            |
| CSS Selector | `By.css("css-selector")`                |
| XPath        | `By.xpath("//tag[@attribute='value']")` |

Ví dụ:

```javascript
let element = driver.findElement(By.id("username"));
```

## **2. Các Thao Tác Cơ Bản**

- **Nhập Dữ Liệu**:
  ```javascript
  await driver.findElement(By.name("q")).sendKeys("Selenium", Key.RETURN);
  ```
- **Nhấp Chuột**:
  ```javascript
  await driver.findElement(By.id("submit-button")).click();
  ```
- **Lấy Nội Dung Văn Bản**:
  ```javascript
  let text = await driver.findElement(By.className("message")).getText();
  console.log(text);
  ```

---

# **Phần 5: Điều Khiển Trình Duyệt**

## **1. Điều Hướng**

```javascript
await driver.get("https://www.example.com");
await driver.navigate().refresh();
await driver.navigate().back();
await driver.navigate().forward();
```

## **2. Chụp Ảnh Màn Hình**

```javascript
await driver.takeScreenshot().then((image) => {
  require("fs").writeFileSync("screenshot.png", image, "base64");
});
```

---

# **Phần 6: Chạy Kiểm Thử với Mocha**

## **1. Cài Đặt Mocha**

```sh
npm install mocha chai
```

Thêm script vào `package.json`:

```json
"scripts": {
  "test": "mocha"
}
```

## **2. Viết Test Case**

Tạo file `test.js`:

```javascript
const { Builder, By, Key, until } = require("selenium-webdriver");
const { expect } = require("chai");

describe("Google Search", function () {
  let driver;

  before(async function () {
    driver = await new Builder().forBrowser("chrome").build();
  });

  after(async function () {
    await driver.quit();
  });

  it("should search for Selenium JavaScript", async function () {
    await driver.get("https://www.google.com");
    let searchBox = await driver.findElement(By.name("q"));
    await searchBox.sendKeys("Selenium JavaScript", Key.RETURN);
    await driver.wait(until.titleContains("Selenium JavaScript"), 5000);

    let title = await driver.getTitle();
    expect(title).to.include("Selenium JavaScript");
  });
});
```

## **3. Chạy Test**

```sh
npm test
```

---

# **Phần 7: Chạy Selenium Trên Docker**

## **1. Cài Đặt Docker**

Tải về từ [Docker](https://www.docker.com/) và cài đặt.

## **2. Chạy Selenium Chrome**

```sh
docker run -d -p 4444:4444 selenium/standalone-chrome
```

## **3. Kết Nối WebDriver Với Docker**

```javascript
let driver = new Builder()
  .forBrowser("chrome")
  .usingServer("http://localhost:4444/wd/hub")
  .build();
```
