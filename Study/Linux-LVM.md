오늘은 **리눅스 환경에서 여러 개의 디스크가 있을 때, 하나의 디스크처럼 사용할 수 있게 설정하는 방법인 LVM(Logical Volume Manager)에 대해 정리한다.** LVM이란 무엇인지 간단히 알아보고, 설정 가이드를 공유한다.

이번 가이드에서 나는 500GB 디스크 4개를 2TB로 묶는 과정을 담았다. LVM을 사용하면 복수개의 디스크를 하나, 혹은 다른 여럿의 디스크로 묶여 사용할 수 있는데, 나는 하나의 디스크로써 어떻게 묶어 사용할 수 있는지에 대한 내용을 담았다.

### **LVM(Logical Volume Manager)이란**

LVM이란 Linux 환경에서 저장 공간(=하드 디스크)를 효과적으로 관리하기 위해 제공하는 제공되는 커널 기능이다. **이 논리적 볼륨 관리자는 이름처럼 실제로 연결된 물리적 볼륨들을 하나의 디스크, 혹은 사용자가 원하는 스토리지 볼륨 그룹으로 나누어 사용할 수 있게 도와준다.** 예를 들어, LVM 기능을 활용하면 지금 내가 하려는 것처럼 4개의 500GB 디스크들을 하나의 2TB 디스크로 구성하는 게 가능하며, 원한다면 2개의 1TB 디스크로 나누어 사용할 수 있게 설정할 수 있다.

LVM을 사용할 때 `물리적 볼륨(PV, Physical Volume)`, `볼륨 그룹(VG, Volume Group)`, `논리적 볼룸(LG, Logical Volume)`에 대해 미리 알아두자. **물리적 볼륨은 서버에 붙어있는 실제 하드 디스크를 의미한다. 볼륨 그룹은 여러 개의 물리적 볼륨을 하나로 묶어 놓은 것을 나타낸다. 마지막으로 논리적 볼륨이란 그룹화된 볼륨을 사용자가 사용 목적에 따라 하나 혹은 여럿의 볼륨으로 나누어 놓은 것이라 할 수 있다.** 자세한 구조는 아래 이미지를 참조하자.
![[Pasted image 20240823111508.png]]
참조 : https://www.thegeekdiary.com/redhat-centos-a-beginners-guide-to-lvm-logical-volume-manager/

### **LVM 설정 가이드**

이번 포스트에서 나는 LVM으로 물리적 볼륨을 하나의 그룹으로 묶어 하나의 논리적 볼륨으로 재구성하고자 한다. 이에 대한 과정은 총 6단계로 나누었다.

1. **디스크 확인**
2. **파티션 생성**
3. **물리적 볼륨 생성**
4. **볼륨 그룹 생성**
5. **논리적 볼륨 생성 및 파일 시스템 할당**
6. **디스크 마운트**

### **1. 디스크 확인**

우선 **서버에 디스크가 잘 연결(Attach)되어 있는지 확인하는 단계**다. 기본적으로 각 볼륨 디바이스는 이름(Name)을 가지고 있는데 앞에 `/dev/` 라는 표기가 생략되어 있음을 알아두자. `ls` 명령어로도 확인이 가능하지만, `lsblk -f` 명령어를 사용할 것을 추천한다. 서버 디스크 목록을 파일 시스템과 마운트 포인트와 함께 상세히 알려준다.

```bash
# 디스크 확인 - 1 (서버의 디스크 목록을 확인할 수 있다.)
ls /dev/xd*
> /dev/xdbf /dev/xdbe /dev/xdba /dev/xdbc

# 디스크 확인 - 2 (디스크의 파일 시스템, 마운트 포인트 등을 확인할 수 있다.)
lsblk -f
```

### **2. 파티션 생성**

서버에 디스크 목록을 확인하였다면, 해당 디스크에 파티션을 생성한다. 파티션이란 하나의 디스크 용량을 어떻게 나누어 사용할지 구분하는 개념이다. 쉽게 말해, 하나의 케익을 얼마만큼 나누어 먹을지 조각내는 것과 같다.

나는 별도로 구분 없이 전체 디스크를 하나의 파티션으로 구성했다. `fdisk` 명령어를 볼륨 디바이스 이름과 함께 입력한다.

```bash
# 파티션 생성 > fdisk
fdisk /dev/xdbf

> Welcome to fdisk (util-linux 2.34).
  Changes will remain in memory only, until you decide to write them.
  Be careful before using the write command.

  Device does not contain a recognized partition table.
  Created a new DOS disklabel with disk identifier 0xa066b799.

  Command (m for help): n
  Partition type
  p primary (0 primary, 0 extended, 4 free)
  e extended (container for logical partitions)
  Select (default p): p
  Partition number (1-4, default 1): 1

  First sector (2048-209715199, default 2048):
  Last sector, +/-sectors or +/-size{K,M,G,T,P} (2048-209715199, default 209715199):

  Created a new partition 1 of type 'Linux' and of size 100 GiB.

  Command (m for help): t
  Selected partition 1
  Hex code (type L to list all codes): 8e
  Changed type of partition 'Linux' to 'Linux LVM'.

  Command (m for help): w
  The partition table has been altered.
  Calling ioctl() to re-read partition table.
  Syncing disks.
```

파티션이 성공적으로 생성되면, 아래 명령어를 통해 결과를 확인할 수 있다.

```bash
# 파티션 생성 결과 - 1
ls /dev/xd*
> /dev/xdbf /dev/xdbf1 /dev/xdbe /dev/xdbe1 /dev/xdba /dev/xdba1 /dev/xdbc /dev/xdbc1

# 파티션 생성 결과 - 2 (출력 결과는 디스크 이름 밑으로 ㄴ> 과 함께 표기된다.)
lsblk -f
```

### **3. 물리적 볼륨 생성**

각 볼륨에 대한 파티션을 생성하면, 물리적 볼륨으로 등록한다. `pvcreate` **명령어를 생성된 파티션 이름과 함께 입력한다**. 이에 대한 결과는 `pvdisplay` 명령어로 확인할 수 있다.

```bash
# 물리적 볼륨 생성
pvcreate /dev/xdbf1
> Physical volume "/dev/xdbf1" successfully created.

pvcreate /dev/xdbe1
> Physical volume "/dev/xdbe1" successfully created.

...
```

```csharp
# 물리적 볼륨 확인
pvdisplay
```

### **4. 볼륨 그룹 생성**

생성된 각 물리적 볼륨을 하나의 그룹으로 묶어 줄 차례다. `vgcreate` 명령어에 생성할 볼륨 그룹명과 물리적 볼륨들을 입력한다. 이후 결과는 `vgdisplay`로 확인할 수 있다. 나는 **node** 라는 볼륨 그룹을 생성했다.

```bash
# 볼륨 그룹 생성
vgcreate node /dev/xdbf1 /dev/xdbe1 /dev/xdba1 /dev/xdbc1
> Volume group "node" successfully created
```

```csharp
# 볼륨 그룹 확인
vgdisplay
```

### **5. 논리적 볼륨 생성 및 파일 시스템 할당**

**볼륨 그룹을 생성한 다음은 논리적 볼륨으로 새로 디스크를 재구성한다.** `lvcreate` 명령어를 내가 생성할 논리적 볼륨 이름과 이에 사용될 물리적 볼륨 그룹 이름을 함께 명시해 실행한다. 마찬가지로 `lvdisplay` 명령어로 결과를 확인하자. 나는 block이라는 논리적 볼륨 그룹을 2TB 용량으로 생성했다.

```scss
# 논리적 볼륨 생성
lvcreate --size 2TB --name block node
> Logical volume "block" created.

# 논리적 볼륨 확인
lvdisplay
```

논리적 볼륨을 확인했다면, 해당 볼륨에 파일 시스템을 생성한다. `mkfs` **명령어에 파일 시스템 타입을 논리적 볼륨 그룹명을 함께 입력한다.** 파일 시스템은 ext4, xfs 두 종류가 있으며, 나는 ext4 타입으로 진행했다.

```yaml
# 논리적 볼륨 포멧 (파일 시스템은 ext4 혹은 xfs 중에서 하나를 선택할 수 잇다.)
mkfs.ext4 /dev/node/block

> mke2fs 1.45.5 (01-Apr-2022)
  Creating filesystem with ...
  Filesystem UUID: ...
  Superblock backups stored on blocks: ...
  Allocating group tables: done
  Writing inode tables: done
  Creating journal (65536 blocks): done
  Writing superblocks and filesystem accounting information: done
```

### **6. 디스크 마운트**

마지막 단계로 생성된 논리적 볼륨을 특정 디렉토리 경로에 마운트 한다. `mount` 명령어에 논리적 볼륨 그룹명과 이에 매핑될 다렉토리 경로를 입력한다.

```bash
# 마운트 할 디렉토리 생성
mkdir /data

# 디스크 마운트
mount /dev/node/block /data
```

위 과정이 모두 성공적으로 끝났다면 **df -h** 명령어로 서버 디스크 목록을 조회했을 때, 우리가 입력한 경로에 디스크가 생성되어 있을 것이다. 만약, 중간에 실수가 있었거나 볼륨을 다시 조직해야 한다면 과정을 역순으로 명령어만 **lvremovem**, **vgremove**, **pvremove** 으로 바꿔서 동일하게 진행하면 된다.