const API_URL = "http://localhost:8080/api/tasks";

async function fetchTasks() {
  const response = await fetch(API_URL);
  const tasks = await response.json();
  renderTasks(tasks);
}

async function addTask() {
  const title = document.getElementById("title").value;
  const description = document.getElementById("description").value;
  const dueDate = document.getElementById("dueDate").value;

  if (!title || !dueDate) {
    alert("Title and due date are required.");
    return;
  }

  await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title, description, dueDate }),
  });

  clearForm();
  fetchTasks();
}

async function updateStatus(id, status) {
  await fetch(`${API_URL}/${id}/status?status=${status}`, {
    method: "PATCH",
  });
  fetchTasks();
}

async function deleteTask(id) {
  await fetch(`${API_URL}/${id}`, {
    method: "DELETE",
  });
  fetchTasks();
}

function renderTasks(tasks) {
  const list = document.getElementById("taskList");
  list.innerHTML = "";

  tasks.forEach((task) => {
    const li = document.createElement("li");

    li.innerHTML = `
      <span class="status">[${task.status}]</span>
      <strong>${task.title}</strong> — ${task.description || ""}
      (Due: ${task.dueDate})
      <div class="actions">
        <button onclick="updateStatus('${task.id}', 'TODO')">TODO</button>
        <button onclick="updateStatus('${task.id}', 'IN_PROGRESS')">IN_PROGRESS</button>
        <button onclick="updateStatus('${task.id}', 'DONE')">DONE</button>
        <button onclick="deleteTask('${task.id}')">Delete</button>
      </div>
    `;

    list.appendChild(li);
  });
}

function clearForm() {
  document.getElementById("title").value = "";
  document.getElementById("description").value = "";
  document.getElementById("dueDate").value = "";
}

// Load tasks on page load
fetchTasks();
