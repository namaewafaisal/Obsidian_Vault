| File                   | Loaded when                                                              |
| ---------------------- | ------------------------------------------------------------------------ |
| `.zshenv`              | Every zsh process                                                        |
| `.zprofile`            | Login shell                                                              |
| `.zshrc`               | Interactive terminal                                                     |
| `user.zsh`             | Whenever HyDE loads interactive shell config                             |
| `environment.d/*.conf` | Once per user session; inherited by apps/services ([Freedesktop.org][1]) |

[1]: https://www.freedesktop.org/software/systemd/man/environment.d.html?utm_source=chatgpt.com "environment.d"
