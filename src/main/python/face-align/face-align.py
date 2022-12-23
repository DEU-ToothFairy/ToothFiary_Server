import os
os.environ['KMP_DUPLICATE_LIB_OK']='True'

import face_alignment
import torch
import cv2
import numpy as np
import matplotlib.pyplot as plt

from skimage import io

# face_detector='sfd' 옵션이랑 device='cpu' 옵션 추가해야 함
# device='gpu'를 써야 더 좋은 것 같은데 쓰면 오류남
fa = face_alignment.FaceAlignment(face_alignment.LandmarksType._2D, flip_input=False, face_detector='sfd', device='cpu')

t = torch.randn(2,2) #일반 버전
#t = torch.randn(2,2).cuda() #쿠다 버전
t.is_cuda  # returns True


def cal_dist(x1, y1, x2, y2, a, b): #점과 선사이의 거리 구하기
    area = (x1-a) * (y2-b) - (y1-b) * (x2 - a)
    AB = ((x1-x2)**2 + (y1-y2)**2) **0.5
    distance = area/AB
    return distance


xmax = [-1,-1] #코
ymax = [-1,-1] #임시턱
zmax = [-1,-1] #임시턱
zzmax = [-1,-1] #찐턱


xy_file = open('C:/Users/joon/Desktop/ToothFiary_Server-master/ToothFiary_Server-master/src/main/python/face-align/test-result/xy.txt', 'w')

input = io.imread('C:/Users/joon/Desktop/ToothFiary_Server-master/ToothFiary_Server-master/src/main/python/face-align/test-asset/test.jpg', pilmode="RGB") #RGB로 변환해줘야
#input = io.imread('C:/Users/joon/Desktop/face-alignment/test-asset/test.jpg')


preds = fa.get_landmarks(input)

fig = plt.figure(figsize=(10, 5))

ax = plt.gca()
ax.axis('off')
ax.axes.xaxis.set_visible(False)
ax.axes.yaxis.set_visible(False)

plt.imshow(input)


c1=0 #총 점 갯수
for i, pred in enumerate(preds):
    #plt.subplot(1, 2, i + 1)
    plt.imshow(input)
    
    for detection in pred:
        plt.scatter(detection[0], detection[1], 1)
        c1+=1
        if detection[0] > xmax[0] : #코1
            xmax[0] = detection[0]
            xmax[1] = detection[1]
        if detection[1] > ymax[1] : #턱
            ymax[0] = detection[0]
            ymax[1] = detection[1]

    for detection in pred:
        if detection[0] > ymax[0] and detection[1] > zmax[1] :
            zmax[0] = detection[0]
            zmax[1] = detection[1]

    for detection in pred:
        if detection[0] > zmax[0] and detection[1] > zzmax[1] :
            zzmax[0] = detection[0]
            zzmax[1] = detection[1]


    plots = np.empty(shape=[0,2]) #배열 초기화
    for detection in pred:  # 턱의 x보다 크면서 y보다 작은 값
        if zzmax[0]<detection[0] and xmax[1] < detection[1] :
            plots = np.append(plots,[[detection[0],detection[1]]], axis=0)        
            

    c2 = 0
    over_mouth_len = 0
    for point in plots :
        if(cal_dist(xmax[0], xmax[1], zzmax[0], zzmax[1], point[0], point[1]) < 0):
            c2 += 1
            over_mouth_len += abs(cal_dist(xmax[0], xmax[1], zzmax[0], zzmax[1], point[0], point[1]))
            #plt.scatter(point[0], point[1],s=5)
    

#plt.scatter(xmax[0], xmax[1], 200)
#plt.scatter(ymax[0], ymax[1], 200)
line = plt.plot([xmax[0],zzmax[0]], [xmax[1],zzmax[1]],color="blue")
plt.setp(line, linewidth = 1.0 , alpha=0.5)

dot_len = 0

print(c1 ,c2, over_mouth_len)
if c2 < 1 :
    dot_len = 0;
else :
    dot_len = round(over_mouth_len/c2,2)       
    
print(dot_len)
#score = 100-c2/c1*100*2 
#print(round(score))

xy_file.write(','.join(str(e) for e in xmax)+'\n'+','.join(str(e) for e in zmax)+'\n'+str(dot_len)+'\n'+str(c2))
xy_file.close()
plt.savefig('C:/Users/joon/Desktop/ToothFiary_Server-master/ToothFiary_Server-master/src/main/python/face-align/test-result/test_graph.png', edgecolor='blue', format='png', dpi=300, bbox_inches='tight', pad_inches=0)        
#plt.savefig('C:/Users/joon/Desktop/face-alignment/test-result/test_graph.png', edgecolor='blue', format='png', dpi=300)
#DPI 해상도
#print(preds)

