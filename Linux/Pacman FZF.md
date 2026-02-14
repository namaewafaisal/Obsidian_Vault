```bash
#!/usr/bin/env bash

# -------- Unified Search (Official + AUR) --------
archpkg() {
  yay -Sl | \
  fzf -m \
      --height=85% \
      --layout=reverse \
      --border \
      --preview '
        repo=$(echo {} | awk "{print \$1}");
        pkg=$(echo {} | awk "{print \$2}");
        if [ "$repo" = "aur" ]; then
          yay -Si "$pkg";
        else
          pacman -Si "$pkg";
        fi
      ' \
      --preview-window=right:60% \
      --bind '
        enter:execute(
          repo=$(echo {} | awk "{print \$1}");
          pkg=$(echo {} | awk "{print \$2}");
          if [ "$repo" = "aur" ]; then
            yay -S "$pkg";
          else
            sudo pacman -S "$pkg";
          fi
        )
      '
}

# -------- Pacman Only --------
pacpkg() {
  pacman -Sl | \
  fzf -m \
      --height=85% \
      --layout=reverse \
      --border \
      --preview '
        pkg=$(echo {} | awk "{print \$2}");
        pacman -Si "$pkg"
      ' \
      --preview-window=right:60% \
      --bind '
        enter:execute(
          pkg=$(echo {} | awk "{print \$2}");
          sudo pacman -S "$pkg"
        )
      '
}

# -------- Remove Installed --------
archrm() {
  pacman -Qe | \
  fzf -m \
      --height=85% \
      --layout=reverse \
      --border \
      --preview '
        pkg=$(echo {} | awk "{print \$1}");
        pacman -Qi "$pkg"
      ' \
      --preview-window=right:60% \
      --bind '
        enter:execute(
          pkg=$(echo {} | awk "{print \$1}");
          sudo pacman -Rns "$pkg"
        )
      '
}

# -------- Command Router --------
case "$1" in
  install) archpkg ;;
  pacman)  pacpkg ;;
  remove)  archrm ;;
  *)
    echo "Usage:"
    echo "  archpkg install   # unified (pacman + AUR)"
    echo "  archpkg pacman    # official repos only"
    echo "  archpkg remove    # remove installed packages"
    ;;
esac
```

