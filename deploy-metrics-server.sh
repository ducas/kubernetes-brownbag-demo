#!/bin/bash

# Installs kubernetes metrics server to kubernetes on your machine.

helm init --wait
helm install stable/metrics-server -n metrics-server --wait
