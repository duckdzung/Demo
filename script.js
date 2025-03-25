$(document).ready(function () {
  let taskList = JSON.parse(localStorage.getItem("tasks")) || [];

  function renderTasks() {
    $("#taskList").empty();
    taskList.forEach((task, index) => {
      $("#taskList").append(`
                <li data-index="${index}">
                    ${task} 
                    <button class="edit">✏️</button>
                    <button class="delete">❌</button>
                </li>
            `);
    });
  }

  function saveTasks() {
    localStorage.setItem("tasks", JSON.stringify(taskList));
  }

  $("#addTaskBtn").click(function () {
    let newTask = $("#taskInput").val().trim();
    if (newTask) {
      taskList.push(newTask);
      saveTasks();
      renderTasks();
      $("#taskInput").val("");
    } else {
      alert("Vui lòng nhập công việc!");
    }
  });

  $("#taskList").on("click", ".delete", function () {
    let index = $(this).parent().data("index");
    taskList.splice(index, 1);
    saveTasks();
    renderTasks();
  });

  $("#taskList").on("click", ".edit", function () {
    let index = $(this).parent().data("index");
    let newTask = prompt("Chỉnh sửa công việc:", taskList[index]);
    if (newTask !== null) {
      taskList[index] = newTask;
      saveTasks();
      renderTasks();
    }
  });

  $("#taskInput").keypress(function (e) {
    if (e.which === 13) {
      $("#addTaskBtn").click();
    }
  });

  renderTasks();
});
