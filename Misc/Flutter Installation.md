cd ~
git clone https://github.com/flutter/flutter.git -b stable flutter-sdk
echo 'export PATH="$PATH:$HOME/flutter-sdk/bin"' >> ~/.bashrc
source ~/.bashrc

---

sudo pacman -Syu --needed \
    gcc clang cmake ninja pkg-config \
    libgl libxcb libx11 \
    libpulse libusb \
    alsa-lib libxi libxrandr \
    gtk3

---




