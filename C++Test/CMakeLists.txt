cmake_minimum_required(VERSION 3.4.1)

add_library(
  # 識別用ライブラリ名を指定
  hello

  # 共有ライブラリとしてビルドさせる
  SHARED

  # C/C++ソースへの相対パス指定
  src/main/cpp/hello.cpp
)

target_link_libraries(
        hello
        android
        log
)