REPL Read, Eval, Print, Loop

echo prints in stdout
echo hi
hi
bash/terminal always in a dir not in void.
pwd print working dir
ls list folders and files (only not hidden)
touch create file
rm delete
rm -i interactive
mv used to move and rename
very dangerous, existing name removes existing file
cd change dir
cd . same
cd .. parent
cd - previous like alt <-
rm * all. like blob get all below
rm string* means start with string all files in the pattern
pres up and down history commands 
history shows all hostory
alias rm = 'rm -i'
like a replacement
clear
. start hidden file or folder
ls -a all
cd ~ home
cd / root
cat prints content of file
grep search a pattern in file or piped output
grep hello text.txt
grep '^start end$'
echo somthing > file overwrite
`>>` append
grep -A1 above -B1 below one line -C both 
grep -i case insensitive
grep -o match the part onely dont printed full line
cat file.txt | grep dave ,  pipe output of cat send input to grep
less paginate. press q to exit / search.
more is similar but actual move page not each entry move
man is manual
man <any number> printf gets the bash or c version
history is from bash so help history is the man
which gets the path of command
type ls gives types maybe
echo -e 'hello'
echo is both built in and library /bin/echo
man echo is for the lib one
type is very helpful
help comand is used for built in