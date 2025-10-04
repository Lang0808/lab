#!/bin/sh

## This file should be executed on new database container, and be in same folder with dump file
mysql -u luke_luke -pluke_password_new luke < old_bin_log.sql