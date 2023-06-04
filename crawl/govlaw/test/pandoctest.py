import subprocess

# 定义输入和输出文件路径
input_file = 'input.docx'
output_file = 'output.html'

# 使用pandoc将Word文档转换为HTML
subprocess.run(['pandoc', input_file, '-o', output_file, '-s'])

with open('output.html', 'r', encoding='utf-8') as f:
    res = f.read()
    print(res)

