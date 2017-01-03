
@copy "C:\Windows\System32\drivers\etc\hosts" "C:\Windows\System32\drivers\etc\hosts_%date:~5,2%%date:~8,2%" 

@copy hosts "C:\Windows\System32\drivers\etc\hosts"

@exit
