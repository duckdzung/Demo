# Hướng Dẫn Sử Dụng jQuery (Phần 1, 2 & 3)

## 1. Giới Thiệu về jQuery

### jQuery là gì?

jQuery là một thư viện JavaScript nhanh, nhỏ gọn và giàu tính năng. Nó giúp dễ dàng thao tác với HTML, xử lý sự kiện, tạo hiệu ứng động và thực hiện Ajax với API đơn giản.

### Tại sao sử dụng jQuery?

- **Dễ sử dụng**: Cú pháp ngắn gọn và đơn giản hơn so với JavaScript thuần.
- **Tương thích trình duyệt**: jQuery giúp xử lý các vấn đề tương thích giữa các trình duyệt.
- **Hiệu suất cao**: Thao tác DOM và Ajax nhanh chóng.
- **Thư viện mở rộng**: Hỗ trợ nhiều plugin mở rộng.

## 2. Cách Nhúng jQuery vào Trang Web

### 2.1. Nhúng từ CDN

```html
<!DOCTYPE html>
<html>
  <head>
    <title>Nhúng jQuery</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </head>
  <body>
    <h1>Xin chào jQuery</h1>
  </body>
</html>
```

### 2.2. Tải về và sử dụng offline

- Tải jQuery từ [jQuery.com](https://jquery.com/).
- Lưu file `.js` vào thư mục dự án.
- Nhúng file bằng thẻ `<script>`:

```html
<script src="js/jquery-3.6.0.min.js"></script>
```

## 3. Cú Pháp Cơ Bản của jQuery

Cú pháp chung của jQuery:

```javascript
$(selector).action();
```

- `$`: Ký hiệu jQuery.
- `selector`: Chọn phần tử HTML.
- `action()`: Hành động thực hiện trên phần tử đó.

Ví dụ:

```javascript
$(document).ready(function () {
  $("p").click(function () {
    alert("Bạn vừa nhấn vào đoạn văn!");
  });
});
```

## 4. Chọn Phần Tử với jQuery Selectors

jQuery cung cấp nhiều cách để chọn phần tử HTML giống như CSS.

| Cú pháp       | Mô tả                               | Ví dụ                             |
| ------------- | ----------------------------------- | --------------------------------- |
| `$("*")`      | Chọn tất cả phần tử                 | `$('*').hide();`                  |
| `$("p")`      | Chọn tất cả thẻ `<p>`               | `$('p').hide();`                  |
| `$(".class")` | Chọn tất cả phần tử có class        | `$('.btn').click();`              |
| `$("#id")`    | Chọn phần tử theo id                | `$('#header').fadeOut();`         |
| `$("ul li")`  | Chọn tất cả `<li>` bên trong `<ul>` | `$('ul li').css('color', 'red');` |

### 4.1. Chọn theo thuộc tính

```javascript
$("input[type='text']").val("Hello!");
```

### 4.2. Chọn phần tử con

```javascript
$("div > p").css("background-color", "yellow");
```

### 4.3. Chọn phần tử tiếp theo

```javascript
$("#myDiv + p").fadeOut();
```

## 5. Thao Tác với HTML

### 5.1. Thay đổi nội dung

- `.html()`: Lấy hoặc thay đổi nội dung HTML.
- `.text()`: Lấy hoặc thay đổi văn bản.
- `.val()`: Lấy hoặc thay đổi giá trị của input.

Ví dụ:

```javascript
$("#btn").click(function () {
  $("#content").html("<b>Nội dung mới</b>");
});
```

### 5.2. Thêm phần tử vào trang

- `.append()`: Thêm vào cuối phần tử.
- `.prepend()`: Thêm vào đầu phần tử.
- `.after()`: Thêm sau phần tử.
- `.before()`: Thêm trước phần tử.

### 5.3. Xóa phần tử

- `.remove()`: Xóa phần tử.
- `.empty()`: Xóa nội dung bên trong phần tử.

## 6. Làm Việc với CSS bằng jQuery

### 6.1. Thêm và xóa class

- `.addClass()`: Thêm class vào phần tử.
- `.removeClass()`: Xóa class khỏi phần tử.
- `.toggleClass()`: Chuyển đổi class (bật/tắt).

### 6.2. Thay đổi CSS trực tiếp

- `.css()`: Thay đổi hoặc lấy giá trị thuộc tính CSS.

## 7. Xử Lý Sự Kiện trong jQuery

### 7.1. Các sự kiện phổ biến

- `click()`, `dblclick()`, `mouseenter()`, `mouseleave()`, `keydown()`, `keyup()`, `change()`

### 7.2. Gán sự kiện bằng `.on()`

## 8. Hiệu Ứng Động trong jQuery

### 8.1. Hiệu ứng hiển thị/ẩn

- `.hide(speed)`, `.show(speed)`, `.toggle(speed)`

### 8.2. Hiệu ứng mờ dần

- `.fadeIn(speed)`, `.fadeOut(speed)`, `.fadeToggle(speed)`, `.fadeTo(speed, opacity)`

### 8.3. Hiệu ứng trượt

- `.slideDown(speed)`, `.slideUp(speed)`, `.slideToggle(speed)`

## 9. AJAX với jQuery

### 9.1. Gửi yêu cầu GET

```javascript
$.get("data.txt", function (data) {
  $("#content").html(data);
});
```

### 9.2. Gửi yêu cầu POST

```javascript
$.post("submit.php", { name: "John" }, function (response) {
  alert("Phản hồi: " + response);
});
```

### 9.3. Sử dụng `.ajax()`

```javascript
$.ajax({
  url: "data.json",
  type: "GET",
  dataType: "json",
  success: function (response) {
    console.log(response);
  },
  error: function (xhr, status, error) {
    console.log("Lỗi: " + error);
  },
});
```

---

Hướng dẫn này đã hoàn tất với các phần:

1. Giới thiệu, cú pháp cơ bản.
2. Thao tác HTML, CSS, sự kiện.
3. Hiệu ứng động và Ajax.

Bạn có thể bắt đầu áp dụng jQuery vào dự án của mình ngay bây giờ! 🚀
