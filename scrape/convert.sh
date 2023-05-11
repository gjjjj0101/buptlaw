#!/usr/bin/env bash

path=/opt/libreoffice*/program/soffice

if [ -a $path ]; then
    echo "LibreOffice found"
else
    echo "LibreOffice not found" >&2
fi
