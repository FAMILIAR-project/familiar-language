#!/bin/sh

dot -Teps $1".dot" -o $1".eps"
open $1".eps"
