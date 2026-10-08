import urllib.request
import os

screens = [
    {
        "id": "0b445cf7e8ed497b830427d8a7658e2e",
        "name": "01_home_todays_workout",
        "img_url": "https://lh3.googleusercontent.com/aida/AEtjO1VD9Spm-X1GRCtC0unUwgtzQAtYXjcQZ516jBLwiEN3eSy9wEG6MXqnVRMtC_ZjKeS8h5Bvnh1ndsDqZiuM-ZRLhG1vDWUiGtJo3dWu0G0E1K_W4SB_LcLIgPtRW0IPIeftqwz4racYZNTdIX3_Bbzvl3r3v8iqv55ZqkZwz-1ee4b3IrQ3WypcbCg10ZkAb5c-sWgUxaWWPh7jVSzvMentJbeVVr_ZNRNiMXVaFcw3gjdJbVdMjBfngg",
        "html_url": "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJxEgxzdGl0Y2hfZmlsZXMaYQosc3RpdGNoX2h0bWxfMDAwNjVkNDEzNTE5OTA2ODAzMzJjOWI1ZDUxNTI2NmISCxIHEOq4oLOGExgBkgEjCgpwcm9qZWN0X2lkEhVCEzg0MjY0NzM1ODI4NDU5MTcyNjg&filename=&opi=89354086"
    },
    {
        "id": "bb87c933fba64be3b0054740d8b7741a",
        "name": "02_active_player",
        "img_url": "https://lh3.googleusercontent.com/aida/AEtjO1X4g84EFZL4TMrBQTslNoHnaFOlHLApxiNuhHERr2KyFB7AERD8kx1wdQapBW0qKAmEPINh63JT7F0E2DKaWzCb75VYuMrrw95PI3PQSqWVVGQy7qugzOmgOK8PfeFLgEkVM3ZwIakNgKArlQgcwLVFo4PMac65zaCPgjfD5P4YOInCWeRAp4BtfUf6oEZHznOcGdBPTIf4UZ-jdRDC0LqjWGh_qzPgbvlgd7KCnCUfckqaIy2ooT_BCWk",
        "html_url": "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJxEgxzdGl0Y2hfZmlsZXMaYQosc3RpdGNoX2h0bWxfMDAwNjVkNDEzNmM1M2U5ZTA2ZmZlNGU5NjcxYmY1M2ESCxIHEOq4oLOGExgBkgEjCgpwcm9qZWN0X2lkEhVCEzg0MjY0NzM1ODI4NDU5MTcyNjg&filename=&opi=89354086"
    },
    {
        "id": "037e079ee64949df8c346194b8382673",
        "name": "03_weekly_schedule",
        "img_url": "https://lh3.googleusercontent.com/aida/AEtjO1Ux1meccBmCAAwqTyPtDFkn3WQ184KkwCT2Z5g9QMy9N3wHdB39GvAJFPOJk_vM1rdIFOSZ-e1OyUyKRoPOmbmyPeRJbMSKIFRN5GZttHSPNvSP750BjUsWGT5c4mjb0Se9E_WfLeiV4TbadQMs-I2qiPRVlJGvo3kDlVDZpeyz3T6CxHtIOSqgtBfALTEv8dhcZKNhQQ9QGQMSC2qEwYKRAWbAkm-qFKM9v-tmcvZQHlDlej0ZYxWXqA",
        "html_url": "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJxEgxzdGl0Y2hfZmlsZXMaYQosc3RpdGNoX2h0bWxfMDAwNjVkNDEzYWI5OWJiYzAzODNhNjQ2MmUyMThiZWQSCxIHEOq4oLOGExgBkgEjCgpwcm9qZWN0X2lkEhVCEzg0MjY0NzM1ODI4NDU5MTcyNjg&filename=&opi=89354086"
    },
    {
        "id": "fa664116c0cb4313aee7ae4f0ad022b7",
        "name": "04_streak_summary",
        "img_url": "https://lh3.googleusercontent.com/aida/AEtjO1VacBWyphTa0aOGY2u17BGY5kq4lTaYH3Y5L1crJ738YxhoKJ6nWIAsFIJWcv-IODvr66HUElUcATgW4nzj6E2CPHz4kGzverSaihQcHBT-S8ziZU49w2BAISOyR-JZQlM7wKuSA9kdqqvFb1sz-3b7gdkb7fPjmRSazmwqcOtJba2nFY-IoAFUC433JZ5yCpj3ON6Cz3ULnXOz3yig0bEjp08C8Vg84kSDE5P_jnD2F96haLTP2s8CI_4",
        "html_url": "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJxEgxzdGl0Y2hfZmlsZXMaYQosc3RpdGNoX2h0bWxfMDAwNjVkNDEzOTU5YWU5NzA3M2FjNzBjNTkxNjUyMjkSCxIHEOq4oLOGExgBkgEjCgpwcm9qZWN0X2lkEhVCEzg0MjY0NzM1ODI4NDU5MTcyNjg&filename=&opi=89354086"
    },
    {
        "id": "8e4cf4596aad42eca9f0ee26b05676d8",
        "name": "05_profile_stats",
        "img_url": "https://lh3.googleusercontent.com/aida/AEtjO1Wo4NtU9GXvrvzgHLk-uJmIcLcux5P5RKYff6amuSmaJYsnhSqq84v2-ydcJGeXO9AKB6t337wu6JjbYg7E4j4tfvFfqKvPsluzNgEQOt_LOOJ0RiOpw1UMPZSlsjAOTK24RuR7D_XYtPS2xloUPaGNtish7KmwHOfiqjl9iyFm2mfgDwbtS1jfqYJMS_9Z3Y4RK7GUtPvQ3OVjLa4-IAjlj4H6-8jn43lMWMZLKS8UbpwwxXKbpPyIDjY",
        "html_url": "https://contribution.usercontent.google.com/download?c=CgthaWRhX2NvZGVmeBJxEgxzdGl0Y2hfZmlsZXMaYQosc3RpdGNoX2h0bWxfMDAwNjVkNDEzZjA0ZmI1MDAzMzg1YWFlYTIxMGMxNzkSCxIHEOq4oLOGExgBkgEjCgpwcm9qZWN0X2lkEhVCEzg0MjY0NzM1ODI4NDU5MTcyNjg&filename=&opi=89354086"
    }
]

out_dir = "/home/arun/Documents/gym/stitch_screens"
os.makedirs(out_dir, exist_ok=True)

headers = {'User-Agent': 'Mozilla/5.0'}

for s in screens:
    img_path = os.path.join(out_dir, f"{s['name']}.png")
    html_path = os.path.join(out_dir, f"{s['name']}.html")
    
    print(f"Downloading {s['name']} image...")
    req = urllib.request.Request(s['img_url'], headers=headers)
    with urllib.request.urlopen(req) as resp, open(img_path, 'wb') as f:
        f.write(resp.read())
        
    print(f"Downloading {s['name']} html...")
    req = urllib.request.Request(s['html_url'], headers=headers)
    with urllib.request.urlopen(req) as resp, open(html_path, 'wb') as f:
        f.write(resp.read())

print("All screens downloaded successfully!")
