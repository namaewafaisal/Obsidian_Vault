---
tags: [javascript, reference, dev]
aliases: [JS]
---

# ⚡ JavaScript — Complete Reference

> [!tip] Quick Nav
> [[#Core Concepts]] · [[#Variables & Types]] · [[#Functions]] · [[#Arrays]] · [[#Objects]] · [[#Async]] · [[#ES6+]] · [[#DOM]] · [[#Error Handling]] · [[#Modules]] · [[#Patterns]]

---

## Core Concepts

| Concept | Description |
|---|---|
| **Interpreted** | Runs line by line, no compile step |
| **Dynamically typed** | Types resolved at runtime |
| **Single-threaded** | One call stack, async via event loop |
| **Prototype-based OOP** | Inheritance via prototype chain |
| **First-class functions** | Functions are values |

### Execution Context & Hoisting
```js
// var → hoisted + initialized as undefined
// let/const → hoisted but NOT initialized (TDZ)
console.log(x); // undefined (not error)
var x = 5;

console.log(y); // ReferenceError: TDZ
let y = 5;

// Function declarations fully hoisted
greet(); // ✅ works
function greet() { return "hi"; }

// Function expressions NOT hoisted
greet(); // ❌ TypeError
const greet = () => "hi";
```

### Scope
```
Global → Function → Block (let/const only)
```
- **Closure** = inner function retains access to outer scope even after outer returns

```js
function counter() {
  let count = 0;
  return () => ++count; // closes over `count`
}
const inc = counter();
inc(); // 1, inc(); // 2
```

---

## Variables & Types

### Declaration
| | `var` | `let` | `const` |
|---|---|---|---|
| Scope | Function | Block | Block |
| Hoisted | ✅ (undefined) | ✅ (TDZ) | ✅ (TDZ) |
| Reassign | ✅ | ✅ | ❌ |
| Re-declare | ✅ | ❌ | ❌ |

### Primitive Types
```js
typeof 42          // "number"
typeof "hi"        // "string"
typeof true        // "boolean"
typeof undefined   // "undefined"
typeof null        // "object" ← JS bug, it's null
typeof Symbol()    // "symbol"
typeof 9007199n    // "bigint"
```

### Falsy Values
```
false, 0, -0, 0n, "", '', ``, null, undefined, NaN
```
Everything else is **truthy** (including `[]`, `{}`, `"0"`)

### Type Coercion Traps
```js
1 + "2"    // "12"  (string wins)
"5" - 2    // 3     (arithmetic coerces)
null == undefined  // true
null === undefined // false
NaN === NaN        // false → use Number.isNaN()
[] == false        // true 😱
```

> [!warning] Always use `===` over `==`

### Nullish & Optional Chaining
```js
const x = null ?? "default"      // "default" (only null/undefined)
const x = 0 ?? "default"         // 0 (unlike ||)

obj?.user?.name                   // undefined if any null/undefined
arr?.[0]                          // safe array access
fn?.()                            // safe call
```

---

## Functions

### Signatures
```js
// Declaration (hoisted)
function add(a, b) { return a + b; }

// Expression
const add = function(a, b) { return a + b; };

// Arrow (no own `this`, `arguments`)
const add = (a, b) => a + b;

// Default params
function greet(name = "World") { return `Hello ${name}`; }

// Rest params
function sum(...nums) { return nums.reduce((a, b) => a + b, 0); }

// Destructured params
function show({ name, age = 0 }) { }
```

### `this` Binding
| Context | `this` |
|---|---|
| Global (non-strict) | `window` |
| Global (strict) | `undefined` |
| Method call | Calling object |
| Arrow function | Inherits from enclosing scope |
| `new` | New instance |
| `.call(obj)` / `.apply(obj)` / `.bind(obj)` | Explicit |

```js
const obj = {
  name: "Faisal",
  greet() { console.log(this.name); },           // ✅ obj
  greetArrow: () => console.log(this.name),      // ❌ outer `this`
};
```

### IIFE
```js
(function() { /* private scope */ })();
(() => { /* same */ })();
```

---

## Arrays

### Creation & Spread
```js
const arr = [1, 2, 3];
const copy = [...arr];
const merged = [...arr1, ...arr2];
Array.from({ length: 5 }, (_, i) => i); // [0,1,2,3,4]
```

### Iteration Methods (return new array / value, don't mutate)
```js
arr.map(x => x * 2)            // transform each → new array
arr.filter(x => x > 2)         // keep matching → new array
arr.reduce((acc, x) => acc + x, 0) // fold → single value
arr.find(x => x > 2)           // first match or undefined
arr.findIndex(x => x > 2)      // index or -1
arr.some(x => x > 2)           // any match → boolean
arr.every(x => x > 2)          // all match → boolean
arr.flat(depth)                 // flatten nested
arr.flatMap(x => [x, x * 2])   // map + flat(1)
arr.forEach(x => ...)           // no return value
```

### Mutation Methods
```js
arr.push(x)        // add end, returns new length
arr.pop()          // remove end, returns removed
arr.unshift(x)     // add start
arr.shift()        // remove start
arr.splice(i, n)   // remove n from index i
arr.sort((a,b) => a-b)  // in-place sort
arr.reverse()      // in-place reverse
```

### Search & Slice
```js
arr.includes(x)               // boolean
arr.indexOf(x)                // index or -1
arr.slice(start, end)         // non-mutating
arr.join(", ")                // → string
[...new Set(arr)]             // deduplicate
```

### Destructuring
```js
const [a, b, ...rest] = [1, 2, 3, 4];
const [, second] = arr;       // skip first
```

---

## Objects

### Creation
```js
const obj = { key: "value" };

// Shorthand
const name = "Faisal";
const user = { name, age: 21 };  // { name: "Faisal", age: 21 }

// Computed keys
const key = "role";
const obj = { [key]: "admin" };
```

### Destructuring
```js
const { name, age = 0 } = user;
const { name: alias } = user;         // rename
const { a: { b } } = nested;         // nested
function fn({ name, role = "user" }) {}
```

### Spread & Merge
```js
const updated = { ...user, age: 22 };  // shallow copy + override
const merged = { ...obj1, ...obj2 };   // later keys win
```

### Iteration
```js
Object.keys(obj)    // ["key1", "key2"]
Object.values(obj)  // [val1, val2]
Object.entries(obj) // [["key1", val1], ...]
Object.fromEntries(entries)  // reverse
```

### Prototype & Classes
```js
class Animal {
  #sound; // private field
  constructor(name, sound) {
    this.name = name;
    this.#sound = sound;
  }
  speak() { return `${this.name}: ${this.#sound}`; }
  static create(name) { return new Animal(name, "..."); }
}

class Dog extends Animal {
  constructor(name) { super(name, "Woof"); }
  speak() { return super.speak() + "!"; }
}
```

---

## Async

### Promise
```js
const p = new Promise((resolve, reject) => {
  // async work
  resolve(value) // or reject(error)
});

p.then(val => ...)
 .catch(err => ...)
 .finally(() => ...);

// Combinators
Promise.all([p1, p2])       // all resolve, or first reject
Promise.allSettled([p1, p2]) // all settle (resolve or reject)
Promise.race([p1, p2])       // first to settle
Promise.any([p1, p2])        // first to resolve
```

### async/await
```js
async function fetchUser(id) {
  try {
    const res = await fetch(`/api/users/${id}`);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    return await res.json();
  } catch (err) {
    console.error(err);
    throw err; // re-throw if needed
  }
}

// Parallel (don't await one-by-one)
const [a, b] = await Promise.all([fetchA(), fetchB()]);
```

### Event Loop
```
Call Stack → Web APIs → Callback Queue → (Microtask Queue first)
```
```js
console.log("1");
setTimeout(() => console.log("3"), 0); // macrotask
Promise.resolve().then(() => console.log("2")); // microtask
// Output: 1, 2, 3
```

---

## ES6+ Features

### Template Literals
```js
`Hello ${name}, you are ${age > 18 ? "adult" : "minor"}`
// Tagged templates
tag`string ${expr}`
```

### Symbols
```js
const id = Symbol("id");  // unique key
obj[id] = 123;            // won't show in for..in
```

### Iterators & Generators
```js
function* range(start, end) {
  for (let i = start; i <= end; i++) yield i;
}
[...range(1, 5)] // [1, 2, 3, 4, 5]
```

### WeakMap / WeakSet
- Keys must be objects, weakly held (GC-able)
- No iteration — used for private data, caches

### Proxy & Reflect
```js
const handler = {
  get: (target, key) => key in target ? target[key] : `${key} not found`,
  set: (target, key, val) => { target[key] = val; return true; }
};
const p = new Proxy({}, handler);
```

### Logical Assignment (ES2021)
```js
a ||= b   // a = a || b
a &&= b   // a = a && b
a ??= b   // a = a ?? b
```

---

## DOM

### Selection
```js
document.getElementById("id")
document.querySelector(".class")       // first match
document.querySelectorAll("div > p")   // NodeList (static)
```

### Manipulation
```js
el.textContent = "text";              // safe (no XSS)
el.innerHTML = "<b>html</b>";         // ⚠️ XSS risk
el.setAttribute("data-id", "1");
el.classList.add("active");
el.classList.toggle("open");
el.style.color = "red";

// Create & insert
const div = document.createElement("div");
parent.appendChild(div);
parent.insertBefore(div, ref);
el.remove();
```

### Events
```js
el.addEventListener("click", handler);
el.removeEventListener("click", handler);

// Delegation (better performance)
document.body.addEventListener("click", (e) => {
  if (e.target.matches(".btn")) { ... }
});

// Event object
e.preventDefault();  // stop default browser action
e.stopPropagation(); // stop bubbling
e.target             // element clicked
e.currentTarget      // element with listener
```

---

## Error Handling

```js
try {
  JSON.parse("{bad}");
} catch (e) {
  if (e instanceof SyntaxError) { ... }
  console.error(e.message, e.stack);
} finally {
  // always runs
}

// Custom errors
class AppError extends Error {
  constructor(message, code) {
    super(message);
    this.name = "AppError";
    this.code = code;
  }
}
throw new AppError("Not found", 404);
```

---

## Modules

```js
// Named exports
export const PI = 3.14;
export function add(a, b) { return a + b; }

// Default export (one per file)
export default class UserService { }

// Import
import { PI, add } from "./math.js";
import UserService from "./UserService.js";
import * as math from "./math.js";

// Dynamic import (lazy loading)
const module = await import("./heavy.js");
```

---

## Patterns

### Debounce & Throttle
```js
// Debounce — delay until stopped calling
function debounce(fn, delay) {
  let timer;
  return (...args) => {
    clearTimeout(timer);
    timer = setTimeout(() => fn(...args), delay);
  };
}

// Throttle — call at most once per interval
function throttle(fn, limit) {
  let last = 0;
  return (...args) => {
    if (Date.now() - last >= limit) {
      last = Date.now();
      fn(...args);
    }
  };
}
```

### Memoization
```js
function memoize(fn) {
  const cache = new Map();
  return (...args) => {
    const key = JSON.stringify(args);
    if (cache.has(key)) return cache.get(key);
    const result = fn(...args);
    cache.set(key, result);
    return result;
  };
}
```

### Observer / Pub-Sub
```js
class EventEmitter {
  #events = {};
  on(event, fn) { (this.#events[event] ??= []).push(fn); }
  emit(event, ...args) { this.#events[event]?.forEach(fn => fn(...args)); }
  off(event, fn) { this.#events[event] = this.#events[event]?.filter(f => f !== fn); }
}
```

---

## Quick Gotchas

> [!warning] Common Traps
> - `typeof null === "object"` — always check `=== null` explicitly
> - `NaN !== NaN` — use `Number.isNaN()`
> - Array `sort()` sorts as strings by default → always pass comparator for numbers
> - `forEach` can't be `break`ed or `return`ed from — use `for...of`
> - `const` with objects/arrays: the **reference** is constant, not the content
> - Arrow functions can't be used as constructors (`new () => {}` → TypeError)
> - Floating point: `0.1 + 0.2 !== 0.3` → use `Math.abs(a - b) < Number.EPSILON`

---

## Cheat Sheet

```js
// Swap variables
[a, b] = [b, a];

// Clone array / object (shallow)
[...arr] / { ...obj }

// Deep clone (modern)
structuredClone(obj)

// Random int in range
Math.floor(Math.random() * (max - min + 1)) + min

// Short circuit eval
value || "default"
value && doSomething()

// Conditional object spread
const obj = { a: 1, ...(condition && { b: 2 }) }

// Convert to number
+str / Number(str) / parseInt(str, 10)

// Convert to string
String(val) / val.toString() / `${val}`

// Check array
Array.isArray(val)

// Unique array
[...new Set(arr)]

// Object to array of entries and back
Object.fromEntries(Object.entries(obj).map(([k, v]) => [k, v * 2]))
```

---
*Last updated: {{date}}*
