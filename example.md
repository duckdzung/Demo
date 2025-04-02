Dưới đây là một bài test hoàn chỉnh cho một ứng dụng **To-Do App** bằng Selenium với JavaScript.

---

# **📌 Kịch Bản Test**

Giả sử chúng ta có một ứng dụng **To-Do App** chạy trên `http://localhost:3000`, có các chức năng sau:

1. Thêm một công việc mới.
2. Đánh dấu công việc đã hoàn thành.
3. Xóa công việc khỏi danh sách.

---

# **🛠 Cấu Trúc Dự Án**

```
selenium-todo-app/
│── package.json
│── test/
│   ├── todo.test.js
│── node_modules/
```

---

# **🛠 Cài Đặt Selenium WebDriver và Mocha**

Trước khi bắt đầu, hãy cài đặt các dependencies:

```sh
npm init -y
npm install selenium-webdriver mocha chai chromedriver
```

---

# **📌 Viết Bài Test**

Tạo file `test/todo.test.js` và thêm nội dung sau:

```javascript
const { Builder, By, Key, until } = require("selenium-webdriver");
const { expect } = require("chai");

describe("To-Do App Tests", function () {
  let driver;

  // Mở trình duyệt trước khi chạy test
  before(async function () {
    driver = await new Builder().forBrowser("chrome").build();
    await driver.get("http://localhost:3000"); // URL của ứng dụng
  });

  // Đóng trình duyệt sau khi hoàn thành test
  after(async function () {
    await driver.quit();
  });

  // Test: Thêm một công việc mới
  it("should add a new todo", async function () {
    let todoInput = await driver.findElement(By.id("todo-input"));
    await todoInput.sendKeys("Learn Selenium", Key.RETURN);

    // Chờ cho item mới xuất hiện
    let newTodo = await driver.wait(
      until.elementLocated(By.xpath("//li[contains(text(),'Learn Selenium')]")),
      5000
    );

    let text = await newTodo.getText();
    expect(text).to.equal("Learn Selenium");
  });

  // Test: Đánh dấu công việc đã hoàn thành
  it("should mark a todo as completed", async function () {
    let checkbox = await driver.findElement(By.css("input[type='checkbox']"));
    await checkbox.click();

    let todoItem = await driver.findElement(
      By.xpath("//li[contains(text(),'Learn Selenium')]")
    );
    let classAttr = await todoItem.getAttribute("class");

    expect(classAttr).to.include("completed"); // Giả sử class "completed" được thêm vào khi hoàn thành
  });

  // Test: Xóa công việc khỏi danh sách
  it("should delete a todo", async function () {
    let deleteButton = await driver.findElement(By.css(".delete-btn"));
    await deleteButton.click();

    // Kiểm tra xem phần tử đã bị xóa chưa
    let todos = await driver.findElements(
      By.xpath("//li[contains(text(),'Learn Selenium')]")
    );
    expect(todos.length).to.equal(0);
  });
});
```

---

# **📌 Chạy Bài Test**

Chạy test bằng lệnh:

```sh
npx mocha test/todo.test.js --timeout 10000
```

> **Lưu ý:** `--timeout 10000` giúp tránh lỗi timeout khi chờ phản hồi từ trình duyệt.

---

# **📌 Giải Thích Chi Tiết**

1. **before()**: Mở trình duyệt Chrome và truy cập `http://localhost:3000`.
2. **after()**: Đóng trình duyệt sau khi test xong.
3. **it("should add a new todo")**:
   - Tìm ô nhập liệu có `id="todo-input"`.
   - Nhập `"Learn Selenium"` rồi nhấn Enter.
   - Kiểm tra xem mục mới đã xuất hiện chưa.
4. **it("should mark a todo as completed")**:
   - Tìm checkbox và click vào.
   - Kiểm tra class `"completed"` được thêm vào mục đó.
5. **it("should delete a todo")**:
   - Tìm nút xóa và click vào.
   - Kiểm tra xem phần tử có còn tồn tại không.
