import re, sys
d = open(sys.argv[1], 'rb').read()
with open(sys.argv[2], 'w') as o:
    for m in re.finditer(rb'[\x09\x0a\x0d\x20-\x7e]{6,}', d):
        s = m.group().decode()
        if s.startswith('_Z'):
            continue
        o.write(f'@0x{m.start():x}: {s}\n----\n' if len(s) > 120 else f'@0x{m.start():x}: {s}\n')
