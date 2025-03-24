Dưới đây là tổng hợp đầy đủ tất cả thông tin về **DataTables.js** mà bạn đã yêu cầu, bao gồm: **column, order, columnDefs, layout (phiên bản mới), render, AJAX, event, pagination, sorting...**

---

# **📌 1. Giới thiệu về DataTables.js**

**DataTables.js** là một thư viện JavaScript mạnh mẽ giúp mở rộng khả năng xử lý dữ liệu của bảng HTML với các tính năng:  
✔ **Tìm kiếm (Search Filter)**  
✔ **Sắp xếp (Sorting)**  
✔ **Phân trang (Pagination)**  
✔ **Tải dữ liệu qua AJAX**  
✔ **Ẩn/hiện cột động (Show/Hide Columns)**  
✔ **Xuất dữ liệu (CSV, Excel, PDF, Print...)**

**Cách khởi tạo đơn giản**:

```javascript
$(document).ready(function () {
  $("#example").DataTable();
});
```

📌 **Lưu ý:** Bạn cần **jQuery** và **DataTables.js** trong dự án.

---

# **📌 2. Khởi tạo bảng với cột (`columns`)**

**Thuộc tính `columns`** dùng để định nghĩa các cột của bảng, bao gồm tên cột, kiểu dữ liệu, và cách hiển thị.

### **📌 Ví dụ: Cấu hình `columns` cơ bản**

```javascript
$("#example").DataTable({
  columns: [
    { title: "ID", data: "id" },
    { title: "Tên", data: "name" },
    { title: "Email", data: "email" },
  ],
});
```

📌 **Giải thích:**

- `title`: Tiêu đề cột.
- `data`: Trường dữ liệu từ nguồn (JSON, Array, etc.).

### **📌 Các thuộc tính của `columns`**

| Thuộc tính   | Mô tả                                 |
| ------------ | ------------------------------------- |
| `title`      | Tiêu đề cột                           |
| `data`       | Trường dữ liệu từ nguồn               |
| `visible`    | `true/false` (Ẩn/hiện cột)            |
| `orderable`  | `true/false` (Có thể sắp xếp không?)  |
| `searchable` | `true/false` (Có thể tìm kiếm không?) |
| `className`  | Thêm CSS class cho cột                |

### **📌 Ví dụ: Ẩn cột và không cho phép sắp xếp**

```javascript
$("#example").DataTable({
  columns: [
    { title: "ID", data: "id", visible: false },
    { title: "Tên", data: "name", orderable: false },
  ],
});
```

---

# **📌 3. Cấu hình `columnDefs` để tùy chỉnh nhiều cột cùng lúc**

- Nếu bạn muốn áp dụng **một thuộc tính cho nhiều cột**, dùng `columnDefs` thay vì `columns`.
- `targets` chỉ định **cột nào được áp dụng**.

### **📌 Ví dụ: Ẩn cột ID và không sắp xếp cột Name**

```javascript
$("#example").DataTable({
  columnDefs: [
    { targets: 0, visible: false }, // Ẩn cột đầu tiên (ID)
    { targets: 1, orderable: false }, // Không cho sắp xếp cột thứ 2 (Tên)
  ],
});
```

📌 **Lưu ý:** `targets` có thể là **số (index)** hoặc **mảng**.

---

# **📌 4. Sắp xếp (`order`)**

- **Mặc định**, DataTables sắp xếp cột đầu tiên theo thứ tự tăng dần.
- Bạn có thể thay đổi bằng thuộc tính `order`.

### **📌 Ví dụ: Sắp xếp cột thứ 2 theo thứ tự giảm dần**

```javascript
$("#example").DataTable({
  order: [[1, "desc"]], // Cột 2, giảm dần
});
```

| Giá trị  | Ý nghĩa          |
| -------- | ---------------- |
| `'asc'`  | Sắp xếp tăng dần |
| `'desc'` | Sắp xếp giảm dần |

---

# **📌 5. Tùy chỉnh hiển thị cột với `render`**

`render` cho phép bạn **tùy chỉnh dữ liệu trước khi hiển thị**.

### **📌 Ví dụ: Format số và thêm tiền tệ**

```javascript
$("#example").DataTable({
  columnDefs: [
    {
      targets: 2,
      render: function (data) {
        return data.toLocaleString() + " VNĐ";
      },
    },
  ],
});
```

📌 **`render` có thể nhận vào:**

- `data`: Giá trị cột
- `type`: Loại truy vấn (`display`, `filter`, `sort`, `type`)
- `row`: Dữ liệu cả dòng
- `meta`: Chứa thông tin index

---

# **📌 6. Load dữ liệu động với AJAX**

- Dữ liệu có thể được lấy từ server qua AJAX.

### **📌 Ví dụ: Gọi API lấy danh sách người dùng**

```javascript
$("#example").DataTable({
  ajax: "https://api.example.com/users",
  columns: [{ data: "id" }, { data: "name" }, { data: "email" }],
});
```

📌 **AJAX hỗ trợ các tùy chọn như:**  
✔ `dataSrc`: Định nghĩa nguồn dữ liệu  
✔ `processing`: Hiển thị loading

---

# **📌 7. Tùy chỉnh bố cục với `layout` (Thay thế `dom` từ phiên bản mới)**

- **Phiên bản mới của DataTables đã thay `dom` bằng `layout`.**
- Cho phép tùy chỉnh các thành phần hiển thị theo `topStart`, `topEnd`, `bottomStart`, `bottomEnd`.

### **📌 Ví dụ: Sắp xếp lại các thành phần**

```javascript
new DataTable("#example", {
  layout: {
    topStart: "search",
    topEnd: "pageLength",
    bottomStart: "info",
    bottomEnd: "paging",
  },
});
```

📌 **Có thể sử dụng mảng để gộp các thành phần:**

```javascript
new DataTable("#example", {
  layout: {
    top: ["search", "pageLength"],
    bottom: ["info", "paging"],
  },
});
```

---

# **📌 8. Gọi lại AJAX khi có sự kiện**

Bạn có thể gọi lại AJAX bằng `ajax.reload()`.

### **📌 Ví dụ: Reload dữ liệu khi nhấn nút**

```javascript
$("#refreshBtn").click(function () {
  $("#example").DataTable().ajax.reload();
});
```

---

# **📌 9. Sự kiện (`event listeners`)**

DataTables hỗ trợ nhiều sự kiện:

### **📌 Ví dụ: Lắng nghe sự kiện khi click vào dòng**

```javascript
$("#example tbody").on("click", "tr", function () {
  var data = $("#example").DataTable().row(this).data();
  alert("Bạn đã chọn: " + data.name);
});
```

---

# **📌 10. Kết hợp với Session trong Thymeleaf**

Nếu bạn dùng **Thymeleaf** trong Spring Boot, bạn có thể lấy session và truyền vào DataTables.

### **📌 Cách lấy Session trong Thymeleaf**

```html
<script>
  var userRole = [[${session.userRole}]];
</script>
```

### **📌 Truyền vào DataTables**

```javascript
$("#example").DataTable({
  columnDefs: [
    { targets: 2, visible: userRole === "admin" }, // Ẩn cột với user không phải admin
  ],
});
```

---

# **📌 Kết luận**

✔ **DataTables.js** giúp bạn tạo bảng mạnh mẽ với **sắp xếp, tìm kiếm, AJAX, ẩn/hiện cột, và bố cục mới (`layout`)**.  
✔ **Phiên bản mới thay thế `dom` bằng `layout`**, giúp cấu hình đơn giản hơn.  
✔ Bạn có thể **kết hợp với Session Thymeleaf** để ẩn/hiện cột động theo quyền user.

📌 **Hãy thử nghiệm và tối ưu cho dự án của bạn! 🚀**
