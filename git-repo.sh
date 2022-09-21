#!/bin/bash

gh repo create my-lowercasePetKinds-svc --public
git init
git remote add origin git@github.com:bmoussaud/my-lowercasePetKinds-svc.git
git branch -M main
git add -A  .
git commit -m "initial commit"
git push -u origin main
open https://github.com/bmoussaud/my-lowercasePetKinds-svc