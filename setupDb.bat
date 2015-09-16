#!/bin/bash
mysql taches -u %1 -p%2 < setup.sql
mysql taches -u %1 -p%2 < populate.sql
