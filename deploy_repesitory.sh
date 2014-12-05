#!/bin/bash
# GitHubPagesにしている簡易MavenRepositryにバイナリをアップするスクリプト。

# まずは、バイナリー一式へとビルド。
sbt clean publish

# Github-Pagesのブランチへ移動。1
git checkout gh-pages

# 設定により、./target/repo にビルドした一式が入っているはずなので、そこからトップへコピー。
cp -R ./target/repo/hm ./

# git commit
git add ./
git commit ./ -m 'Build and deploy Binally.'
git push

# ブランチを元に戻す。
git checkout master


