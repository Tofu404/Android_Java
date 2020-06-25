### git的使用，官方的文档地址：https://git-scm.com/book/zh/v2
## 在GitHub / GitLab上创建仓库。
1. 创建新仓库。

2. 将仓库克隆到本地 
    - git clone + github上的克隆地址
    - ### 在要是使用https协议克隆的仓库，在每次push的时候会要求重新输入GitHub的账号和密码
    - ### 在使用ssh协议的话，只需要在第一次使用的时候允许是使用，之后就不用每次输入GitHub的账号和密码
        - 创建SSH key：https://git-scm.com/book/zh/v2/%E6%9C%8D%E5%8A%A1%E5%99%A8%E4%B8%8A%E7%9A%84-Git-%E7%94%9F%E6%88%90-SSH-%E5%85%AC%E9%92%A5
        - 创建SSH key之后，我们在GitHub的设置里头，添加SSH key

3. 将本地的项目上传到远端仓库
    - 第一步：初始化git(git init)
    - 第二步：将工作区下的文件添加到版本库的暂存区（git add + 文件名/ git add .）
    - 第三步：将暂存区的文件提交到分支（git commit -m "简单描述提交的信息"）
    - 第四步：将本地文件上传到远端服务器（git push -u origin master，这里的就是将本地的master分支，上传到远端服务器，“-u”的意思是设置默认origin主机，下次使用的时候就是可以直接使用“git push” 命令）
4. 将远端的数据拉取下来（git pull / git pull --rebase）
    - git pull 和 git pull --rebase的区别：
        - git pull = git fetch + git merge
        - git pull --rebase = git fetch + git reabase
        - git pull --rebase 比 git pull 减少了不必要代码的合并，使得开发分支像是在一条直线上

5. 查看当前的分支的状态（git status）

6. 放弃当前的修改（git stash）

7. 查看当前的git提交的日志（git log）

8. 合并冲突
    - 原因：由于多个协作的时候，A和B和C……同时提交修改，导致修改后的内容有相似的地方，形成了冲突的发生，
      git在冲突发生的时候以“<<<<<<<<< HEAD”，“=======”，“>>>>>>>>> + 分支名称”， 的符号给出冲突发生的位置

    - 解决方法：在提交的时候先将队友的上传的代码先拉取下来（git pull --rebase），在本地将有冲突的地方修改完成之后再上传上去（git push）

9. 分支回滚：将头节点指向想要恢复的分支
    - git reset --hard HEAD^(HEAD^^ 表示上上一个节点，HEAD~3表示往上的第三个节点，以此类推)

    - 回退之后，回到回退之前的分支（git reset --+分支的id）

    - 查看历史命令（git reflog），这样我们就可以看到我们操作的历史记录详情

10. 合并分支
    - 将某分支合并到该分支上
    - 具体操作：假设有一个b1的分支要合并到master分支。首先要将当前分支切换到master分支，然后在master分支上（git merge b1），这样就将b1分支合并到master分支上了。

11. 工作区和缓存区概念 

![](image/0.jpg)

- 工作区就是我们本地代码存放的目录

- 在.git文件中存放着版本库（.git文件默认是隐藏的）。在版本库中的有两个区块
    - 一个是：stage缓存区，我们git add命令就是将修改后的文件添加到这个区域
    - 一个是：git自动为我们创建的master分支，当然我们可以用（git checkout +分支）切换到指定的分支。我们使用（git commit -m "编写的备注信息"）将stage中的缓存信息提交到指定的分支
