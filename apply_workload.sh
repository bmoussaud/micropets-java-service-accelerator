#--source-image imageRegistry/micropet-tap-lowercasePetKind-sources
tanzu apps workload apply -f config/workload.yaml --live-update --local-path .  --namespace devNamespace --yes 

