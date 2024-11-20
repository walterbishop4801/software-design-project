with open('CHANGELOG.md', 'r') as file:
    lines = file.readlines()

cleaned_lines = []
skip_other_changes = False

for i, line in enumerate(lines):
    # Skip "## Other Changes" sections without a list item
    if "## Other Changes" in line:
        skip_other_changes = not any(lines[i+1:].strip().startswith('-') for line in lines[i+1:] if line.strip())
        continue

    if not skip_other_changes:
        # Add non-blank lines
        if line.strip():
            cleaned_lines.append(line)
        # Add a blank line if the last line is not a blank line
        elif cleaned_lines and cleaned_lines[-1].strip() != "":
            cleaned_lines.append(line)

    skip_other_changes = False

# Remove trailing blank lines
while cleaned_lines and not cleaned_lines[-1].strip():
    cleaned_lines.pop()

with open('CHANGELOG.md', 'w') as file:
    file.writelines(cleaned_lines)
