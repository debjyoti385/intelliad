#!/bin/bash
f='imContent.csv'

# Ad categories
#0: Entertainment  3: Social      6: Utility
#1: Games	   4: Products       	
#2: News           5: Travel           

cat $f | grep -i 'video\|movie\|channel\|theatre' | grep -iv "recharge" > $f"-0"
cat $f | grep -i 'game\|play' | grep -iv 'google play' > $f"-1"
cat $f | grep -i 'news' > $f"-2"
cat $f | grep -i 'chat\|friends' > $f"-3"
cat $f | grep -i 'gadget\|electronic\|iphone\|lumia\|samsung\|shop\|buy' > $f"-4"
cat $f | grep -i 'hotel\|nightstay\|lodge\|holiday' > $f"-5"
cat $f | grep -i 'speed\|memory\|bank\|weather\|calculator\|update' > $f"-6"
