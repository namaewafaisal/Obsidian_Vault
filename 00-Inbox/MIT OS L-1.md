Intro

## Lecture 1 - File Descriptors & Basic I/O

- fd (File Descriptor)
  - Small integer returned by `open()`.
  - Each process has its own FD table.
  - FD table entry → points to an open file object.
  - Open file object stores:
    - current offset (read/write position)
    - open mode (read/write)
    - inode (actual file)
  - Avoids searching by filename on every `read()`/`write()`.

- `int fd = open("file.txt", O_WRONLY);`
  - `fd` → variable storing the file descriptor.
  - `"file.txt"` → filename/path.
  - `O_WRONLY` → open for writing.
  - Returns lowest available FD (usually 3).

- Default File Descriptors
  - `0` → stdin
  - `1` → stdout
  - `2` → stderr

- `read(fd, buf, n)`
  - Reads **up to** `n` bytes into `buf`.
  - Returns:
    - `>0` → bytes read
    - `0` → EOF
    - `-1` → error
  - Automatically advances the file offset.

- `write(fd, buf, n)`
  - Writes `n` bytes from `buf`.
  - Does **not** reset or affect the read offset.

- Buffer
  - Temporary RAM used to hold data during I/O.
  - Same buffer reused every iteration.
  - Large files are processed in chunks.

- EOF (End Of File)
  - File: reached last byte.
  - Terminal: `Ctrl + D` sends EOF.

- Shell Redirection
  - `>` redirects stdout (fd 1) to a file.
  - `<` redirects stdin (fd 0) from a file.
  - Programs don't know the difference—they just use file descriptors.

- Unix Philosophy
  - Everything is treated as a byte stream.
  - Same `read()` / `write()` interface works for:
    - files
    - terminal
    - pipes
    - sockets

- Linux Files
  - File extensions are optional.
  - Kernel identifies files by metadata/content, not extension.
  - `out`, `notes.txt`, `abc.xyz` are all just filenames.

## Processes & fork()

- Process
  - Running instance of a program.
  - Has its own memory, registers, stack, heap and FD table.

- `fork()`
  - Creates a new process (child).
  - Child is an almost exact copy of the parent.
  - Both continue executing from the **next instruction** after `fork()`.

- `pid = fork()`
  - `pid` is **NOT** the process ID.
  - `pid` stores the **return value of `fork()`**.
  - Parent: `pid > 0` (child's actual PID).
  - Child: `pid == 0`.
  - Error: `pid == -1`.

- Actual Process ID
  - Every process has a unique PID assigned by the OS.
  - Doesn't change during the process lifetime.
  - Obtain using `getpid()`.

- Memory after `fork()`
  - Entire process memory is copied.
  - Variables initially have the same values.
  - Only the return value of `fork()` differs between parent and child.

- Multiple `fork()`
  - Every existing process executes the next `fork()`.
  - Number of processes:
    - 1 fork → 2 processes
    - 2 forks → 4 processes
    - 3 forks → 8 processes (`2^n`)

- Scheduling
  - Parent and child run independently.
  - Execution order is **not guaranteed**.
  - Parent may run first, child may run first, or execution may interleave.

- Output
  - Linux usually buffers `printf()`, so output often appears line-by-line.
  - xv6 may interleave characters because both processes write concurrently with minimal buffering.


---

## Environment

- QEMU → Hardware/CPU emulator.
- xv6 → Minimal Unix-like OS for learning.
- Architecture → RISC-V.

