---
tags: [html, web-development, frontend, notes]
created: 2026-08-14
---

# HTML Basics

## 🌐 How the Web Works

**Request-Response Cycle:**

```
Browser → HTTP Request → Web Server → HTTP Response → Browser
```

### Anatomy of a URL

```
https://www.example.com:3000/user/25
```

| Part | Example | Meaning |
|------|---------|---------|
| **Protocol** | `https://` | How data is transferred |
| **Domain** | `www.example.com` | Web address |
| **Port** | `3000` | Which resource on the server |
| **Path** | `/user` | Route you're navigating to |
| **Parameters** | `/25` | Specific data passed to the path |

---

## 🧱 Document Structure

```html
<!DOCTYPE html>
```
- Declares which **version** of HTML the page uses

```html
<head>
  <title>Page Title</title>
  <meta name="description" content="...">
  <link rel="icon" href="favicon.ico">
</head>
```
- `<head>` → holds important links/metadata (not visible on page)
- `<title>` → tab title
- `<meta>` → SEO/description text for the page
- `<link rel="icon">` → creates the **favicon** (browser tab icon)

```html
<hr>
```
- Creates a **horizontal line/rule**

---

## 🔗 Links & Embeds

```html
<a href="https://example.com">My Link</a>
```
- `<a>` → **anchor tag**, provides a hyperlink
- `href` → the destination URL

```html
<iframe src="https://example.com"></iframe>
```
- `<iframe>` → embeds/displays **another website inside your web page**

---

## 📊 Tables

```html
<table border="1">
  <tr>
    <td>Name</td>
    <td>Age</td>
  </tr>
  <tr>
    <td>XXX</td>
    <td>20</td>
  </tr>
</table>
```

- `<table>` → creates the table (`border="1"` adds visible borders)
- `<tr>` → table row
- `<td>` → table data/cell

---

## 📝 Forms

```html
<form>
  Name: <input type="text" placeholder="Enter your name">
  <br>
  Age: <input type="number">
  <input type="radio" name="gender"> Male
</form>
```

- `<form>` → wraps all input elements
- `<input type="text">` → free text entry
- `<input type="number">` → numeric-only entry
- `<input type="radio">` → single-choice option (e.g., Male/Female)
- `<br>` → **line break**, moves to a new line
- `placeholder="..."` → shows **faded hint text** inside the input (an "illusion" of text — disappears when user types)

### Dropdowns

```html
<select>
  <option>Option 1</option>
  <option>Option 2</option>
</select>
```
- `<select>` → creates a dropdown
- `<option>` → each choice inside the dropdown

---

## 🔑 Quick Recap
- [[HTTP Basics]] — request/response cycle, URL structure
- `<a>`, `<iframe>` → links & embedded content
- `<table>`, `<tr>`, `<td>` → tabular data
- `<form>`, `<input>`, `<select>`, `<option>` → user input
