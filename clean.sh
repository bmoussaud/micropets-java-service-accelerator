#!/bin/bash

kubectl delete -f config/workload.yaml
gh repo delete my-lowercasePetKind-svc --confirm