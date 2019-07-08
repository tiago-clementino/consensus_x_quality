library(tidyverse)
library(lubridate)
library(here)
library(gridExtra)
library(grid)
theme_set(theme_bw())

forum_ed = read_csv("data/out_editiondistance.csv", quote= '"')

forum_disco = read_csv("data/out_DISCO.csv", quote= '"')

forum_wordnet = read_csv("data/out_wordnet.csv", quote= '"')

getmode <- function(x) {
    uniqx <- unique(na.omit(x))
    uniqx[which.max(tabulate(match(x, uniqx)))]
}

namevector <- c("staffright")
forum_ed[ , namevector] <- 0
forum_disco[ , namevector] <- 0
forum_wordnet[ , namevector] <- 0



threads_ed = forum_ed %>% 
    group_by(commentthreadid) %>% 
    mutate(staffcount = sum(if_else(staff,1,0,0)),
           opinionmode = getmode(firstopinionid))

threads_disco = forum_disco %>% 
    group_by(commentthreadid) %>% 
    mutate(staffcount = sum(if_else(staff,1,0,0)),
           opinionmode = getmode(firstopinionid))

threads_wordnet = forum_wordnet %>% 
    group_by(commentthreadid) %>% 
    mutate(staffcount = sum(if_else(staff,1,0,0)),
           opinionmode = getmode(firstopinionid))



threads_ed = threads_ed %>% 
    mutate(iamright = if(firstopinionid==opinionmode) 1 else 0)

threads_disco = threads_disco %>% 
    mutate(iamright = if(firstopinionid==opinionmode) 1 else 0)

threads_wordnet = threads_wordnet %>% 
    mutate(iamright = if(firstopinionid==opinionmode) 1 else 0)



threads_ed = threads_ed %>% 
    group_by(commentthreadid) %>% 
    mutate(staffright = sum(if(staff && firstopinionid==opinionmode) 1 else 0),
           total_posts = n())

threads_disco = threads_disco %>% 
    group_by(commentthreadid) %>% 
    mutate(staffright = sum(if(staff && firstopinionid==opinionmode) 1 else 0),
           total_posts = n())

threads_wordnet = threads_wordnet %>% 
    group_by(commentthreadid) %>% 
    mutate(staffright = sum(if(staff && firstopinionid==opinionmode) 1 else 0),
           total_posts = n())






threads_ed = threads_ed %>% 
    mutate(iagree_1 = if(staffcount>0 && iamright && staffright==staffcount) 1 else 0,
           iagree_2 = if(staffcount>0 && iamright && staffright>staffcount/2) 1 else 0,
           iagree_3 = if(staffcount>0 && staffright>staffcount/2) 1 else 0,
           iagree_4 = if(staffcount>0) staffright/staffcount else -1,
           iagree_5 = if(staffcount>0 && staffright/staffcount>=1/countopinions) 1 else 0)

threads_disco = threads_disco %>% 
    mutate(iagree_1 = if(staffcount>0 && iamright && staffright==staffcount) 1 else 0,
           iagree_2 = if(staffcount>0 && iamright && staffright>staffcount/2) 1 else 0,
           iagree_3 = if(staffcount>0 && staffright>staffcount/2) 1 else 0,
           iagree_4 = if(staffcount>0) staffright/staffcount else -1,
           iagree_5 = if(staffcount>0 && staffright/staffcount>=1/countopinions) 1 else 0)

threads_wordnet = threads_wordnet %>% 
    mutate(iagree_1 = if(staffcount>0 && iamright && staffright==staffcount) 1 else 0,
           iagree_2 = if(staffcount>0 && iamright && staffright>staffcount/2) 1 else 0,
           iagree_3 = if(staffcount>0 && staffright>staffcount/2) 1 else 0,
           iagree_4 = if(staffcount>0) staffright/staffcount else -1,
           iagree_5 = if(staffcount>0 && staffright/staffcount>=1/countopinions) 1 else 0)






threads_ed = threads_ed %>% 
    group_by(total_posts) %>% 
    mutate(post_class = if(total_posts<=5) 1 else 0)


threads_disco = threads_disco %>% 
    group_by(total_posts) %>% 
    mutate(post_class = if(total_posts<=5) 1 else 0)


threads_wordnet = threads_wordnet %>% 
    group_by(total_posts) %>% 
    mutate(post_class = if(total_posts<=5) 1 else 0)


threads_ed %>% 
    write_csv(here::here("data/threads_3_ed.csv"))

threads_ed_2 = threads_ed %>% filter(staffcount>0) 
threads_ed_2$opinionmode
threads_ed_2$staffcount
threads_ed_2$consensus = (threads_ed_2$consensus - 0.5)*2.0
threads_ed_2$confusionaverage = ((threads_ed_2$confusionaverage)/7.0)


threads_ed_2 = threads_ed_2 %>% 
    mutate(consensus_4 = if(consensus>=0.7) 1 else 0,
           consensus_3 = if(consensus>=0.8) 1 else 0,
           consensus_2 = if(consensus>=0.8) 1 else 0)

threads_disco %>% 
    write_csv(here::here("data/threads_3_disco.csv"))

threads_disco_2 = threads_disco %>% filter(staffcount>0) 
threads_disco_2$opinionmode
threads_disco_2$staffcount
threads_disco_2$consensus = (threads_disco_2$consensus - 0.5)*2.0
threads_disco_2$confusionaverage = ((threads_disco_2$confusionaverage)/7.0)



threads_disco_2 = threads_disco_2 %>% 
    mutate(consensus_4 = if(consensus>=0.7) 1 else 0,
           consensus_3 = if(consensus>=0.8) 1 else 0,
           consensus_2 = if(consensus>=0.8) 1 else 0)

threads_wordnet %>% 
    write_csv(here::here("data/threads_3_wordnet.csv"))

threads_wordnet_2 = threads_wordnet %>% filter(staffcount>0) 
threads_wordnet_2$opinionmode
threads_wordnet_2$staffcount
threads_wordnet_2$consensus = (threads_wordnet_2$consensus - 0.5)*2.0
threads_wordnet_2$confusionaverage = ((threads_wordnet_2$confusionaverage)/7.0)


threads_wordnet_2 = threads_wordnet_2 %>% 
    mutate(consensus_4 = if(consensus>=0.7) 1 else 0,
           consensus_3 = if(consensus>=0.8) 1 else 0,
           consensus_2 = if(consensus>=0.8) 1 else 0)




threads_ed_2 %>%  
    write_csv(here::here("data/threads_4_ed.csv"))

threads_disco_2 %>% 
    write_csv(here::here("data/threads_4_disco.csv"))

threads_wordnet_2 %>% 
    write_csv(here::here("data/threads_4_wordnet.csv"))

